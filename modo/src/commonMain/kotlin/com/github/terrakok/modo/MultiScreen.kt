package com.github.terrakok.modo

/**
 * Screen that holds a stack of NavigationState.
 *
 * You can switch between NavigationStates by changing [selectedStack] value.
 */
data class MultiScreen(
    override val id: String,
    val stacks: List<NavigationState>,
    val selectedStack: Int
) : Screen

// TODO leave only one MultiScreen and fix naming
/**
 * A Screen that is also a container multi-stack navigation.
 * Has several properties for initial NavigationStateMultiScreenEntry configuration.
 */
interface MultiScreenNew : Screen {
    override val id: String
    val stacks: List<NavigationState>
    val selectedStack: Int
}

/**
 * Navigation entry holding a screen with multi-stack navigation.
 */
data class NavigationStateMultiScreenEntry(
    val multiScreen: MultiScreenNew,
    val stacks: List<NavigationState>,
    val selectedStack: Int
) : NavigationStateEntry {

    override val screen: Screen
        get() = multiScreen
}

class ExternalForward(val screen: Screen, vararg val screens: Screen) : NavigationAction
object BackToLocalRoot : NavigationAction
class SelectStack(val stackIndex: Int) : NavigationAction

fun Modo.externalForward(screen: Screen, vararg screens: Screen) = dispatch(ExternalForward(screen, *screens))
fun Modo.selectStack(stackIndex: Int) = dispatch(SelectStack(stackIndex))
fun Modo.backToLocalRoot() = dispatch(BackToLocalRoot)

class MultiReducer(
    private val origin: ModoReducer = ModoReducer { screen ->
        if (screen is MultiScreenNew) {
            NavigationStateMultiScreenEntry(
                multiScreen = screen,
                stacks = screen.stacks,
                selectedStack = screen.selectedStack
            )
        } else {
            NavigationStateScreenEntry(screen)
        }
    }
) : NavigationReducer {
    override fun invoke(action: NavigationAction, state: NavigationState) = when (action) {
        is Exit, is BackToRoot, is NewStack -> origin(action, state)
        is ExternalForward -> origin(Forward(action.screen, *action.screens), state)
        is Forward, is Replace, is Back -> applyLocalAction(action, state)
        is BackToLocalRoot -> applyLocalAction(BackToRoot, state)
        is BackTo -> applyBackToAction(action, state) ?: state
        is SelectStack -> applySelectStackAction(action, state)
        else -> state
    }

    private fun applyLocalAction(
        action: NavigationAction,
        state: NavigationState
    ): NavigationState {
        val localNavigationState = getLocalNavigationState(state)
        val newLocalNavigationState = origin(action, localNavigationState)
        return applyNewLocalNavigationState(state, newLocalNavigationState)
    }

    private fun getLocalNavigationState(state: NavigationState): NavigationState {
        val entry = state.chain.lastOrNull()
        return if (entry is NavigationStateMultiScreenEntry) {
            getLocalNavigationState(entry.stacks[entry.selectedStack])
        } else {
            state
        }
    }

    private fun applyNewLocalNavigationState(
        state: NavigationState,
        local: NavigationState
    ): NavigationState {
        val entry = state.chain.lastOrNull()
        return if (entry is NavigationStateMultiScreenEntry) {
            val selectedStack = entry.stacks[entry.selectedStack]
            val newLocalChain = applyNewLocalNavigationState(selectedStack, local)

            if (newLocalChain.chain.isNotEmpty()) {
                val newStacks = entry.stacks.toMutableList()
                newStacks[entry.selectedStack] = newLocalChain
                val newMultiScreen = entry.copy(stacks = newStacks)

                val newChain = state.chain.dropLast(1).plus(newMultiScreen)
                NavigationState(newChain)
            } else {
                val newChain = state.chain.dropLast(1)
                NavigationState(newChain)
            }
        } else {
            local
        }
    }

    private fun applyBackToAction(action: BackTo, state: NavigationState): NavigationState? {
        val entry = state.chain.lastOrNull() ?: return null
        return if (entry is NavigationStateMultiScreenEntry) {
            val newLocalChain = applyBackToAction(action, entry.stacks[entry.selectedStack])
            if (newLocalChain == null) {
                if (state.chain.none { it.screen.id == action.screenId }) {
                    return null
                } else {
                    origin(action, state)
                }
            } else {
                val newStacks = entry.stacks.toMutableList()
                newStacks[entry.selectedStack] = newLocalChain
                val newMultiScreen = entry.copy(stacks = newStacks)

                val newChain = state.chain.dropLast(1).plus(newMultiScreen)
                NavigationState(newChain)
            }
        } else {
            if (state.chain.none { it.screen.id == action.screenId }) {
                null
            } else {
                origin(action, state)
            }
        }
    }

    private fun applySelectStackAction(
        action: SelectStack,
        state: NavigationState
    ): NavigationState {
        val entry = state.chain.lastOrNull()
        return if (entry is NavigationStateMultiScreenEntry) {
            val selectedStack = entry.stacks[entry.selectedStack]
            if (selectedStack.chain.lastOrNull() is NavigationStateMultiScreenEntry) {
                val newStacks = entry.stacks.toMutableList()
                val newLocalChain = applySelectStackAction(action, selectedStack)
                newStacks[entry.selectedStack] = newLocalChain
                val newMultiScreen = entry.copy(stacks = newStacks)

                val newChain = state.chain.dropLast(1).plus(newMultiScreen)
                NavigationState(newChain)
            } else {
                val newMultiScreen = entry.copy(selectedStack = action.stackIndex)
                val newChain = state.chain.dropLast(1).plus(newMultiScreen)
                NavigationState(newChain)
            }
        } else {
            state
        }
    }
}