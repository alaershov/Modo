package com.github.terrakok.modo.android.compose

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.github.terrakok.modo.*

// TODO handle app finish on empty nav state
@Composable
fun MainModoRender(
    modo: Modo,
    render: @Composable (navigationState: NavigationState, content: @Composable () -> Unit) -> Unit
) {
    val navigationState: NavigationState by modo.observeAsState()
    ModoRender(modo, navigationState, render)
}

@Composable
fun ModoRender(
    modo: Modo,
    navigationState: NavigationState,
    render: @Composable (navigationState: NavigationState, content: @Composable () -> Unit) -> Unit
) {
    navigationState.chain.lastOrNull()?.let { navigationStateEntry ->
        val screen = navigationStateEntry.screen

        when (navigationStateEntry) {
            is NavigationStateScreenEntry -> {
                when (screen) {
                    is ComposeScreen -> render(navigationState) { screen.Content(modo) }
                    else -> error("ModoRender for NavigationStateScreenEntry works with ComposeScreen only! Received $screen")
                }
            }
            is NavigationStateMultiScreenEntry -> {
                when (screen) {
                    is ComposeMultiScreen -> render(navigationState) { screen.Content(modo, navigationStateEntry) }
                    else -> error("ModoRender for NavigationStateMultiScreenEntry works with ComposeMultiScreen only! Received $screen")
                }
            }
        }
    }
}

@Composable
fun Modo.observeAsState(): State<NavigationState> {
    val modo = this
    val lifecycleOwner = LocalLifecycleOwner.current
    val state = remember { mutableStateOf(modo.state) }
    DisposableEffect(modo, lifecycleOwner) {
        modo.render = { navigationState -> state.value = navigationState }
        onDispose { modo.render = null }
    }
    return state
}
