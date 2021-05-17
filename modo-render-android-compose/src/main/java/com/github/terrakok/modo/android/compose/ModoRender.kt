package com.github.terrakok.modo.android.compose

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.MultiScreen
import com.github.terrakok.modo.NavigationState

// TODO handle app finish on empty nav state
@Composable
fun ModoRender(
    navigationState: NavigationState,
) {
    navigationState.chain.lastOrNull()?.let { screen ->
        when (screen) {
            is ComposeScreen -> screen.Content()
            is MultiScreen -> {
                val stack = remember { screen.stacks[screen.selectedStack] }
                ModoRender(stack)
            }
            else -> error("ComposeRender works with ComposeScreen only! Received $screen")
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
