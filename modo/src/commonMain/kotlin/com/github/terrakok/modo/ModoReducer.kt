package com.github.terrakok.modo

/**
 * Main reducer with logic for basic navigation actions
 */
class ModoReducer(
    private val screenToEntry: (Screen) -> NavigationStateEntry = { NavigationStateScreenEntry(it) }
) : NavigationReducer {
    override fun invoke(action: NavigationAction, state: NavigationState): NavigationState =
        when (action) {
            is Forward -> NavigationState(
                state.chain + listOf(action.screen.asEntry()) + action.screens.map { it.asEntry() }
            )
            is Replace -> NavigationState(
                state.chain.dropLast(1) + listOf(action.screen.asEntry()) + action.screens.map { it.asEntry() }
            )
            is NewStack -> NavigationState(
                listOf(action.screen.asEntry()) + action.screens.map { it.asEntry() }
            )
            is BackTo -> {
                val i = state.chain.indexOfLast { it.screen.id == action.screenId }
                if (i != -1) NavigationState(state.chain.take(i + 1))
                else state
            }
            is BackToRoot -> NavigationState(
                listOfNotNull(state.chain.firstOrNull())
            )
            is Back -> NavigationState(
                state.chain.dropLast(1)
            )
            is Exit -> NavigationState()
            else -> state
        }

    private fun Screen.asEntry(): NavigationStateEntry {
        return screenToEntry(this)
    }
}