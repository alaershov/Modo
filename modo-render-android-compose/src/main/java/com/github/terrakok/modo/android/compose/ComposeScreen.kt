package com.github.terrakok.modo.android.compose

import android.os.Parcelable
import androidx.compose.runtime.Composable
import com.github.terrakok.modo.MultiScreen
import com.github.terrakok.modo.NavigationState
import com.github.terrakok.modo.Screen

abstract class ComposeScreen(
    override val id: String
) : Screen, Parcelable {
    @Composable
    abstract fun Content()

    override fun toString() = "[$id]"
}

fun MultiComposeScreen(
    id: String,
    roots: List<ComposeScreen>,
    selected: Int
) = MultiScreen(
    id,
    List(roots.size) { i -> NavigationState(listOf(roots[i])) },
    selected
)

fun FlowComposeScreen(
    id: String,
    root: ComposeScreen
) = MultiScreen(
    id,
    listOf(NavigationState(listOf(root))),
    0
)
