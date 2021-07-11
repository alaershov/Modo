package com.github.terrakok.modo.android.compose

import android.os.Parcelable
import androidx.compose.runtime.Composable
import com.github.terrakok.modo.*

abstract class ComposeScreen(
    override val id: String
) : Screen, Parcelable {

    @Composable
    abstract fun Content(modo: Modo)

    override fun toString() = "[$id]"
}

abstract class ComposeMultiScreen(
    override val id: String,
    override val stacks: List<NavigationState>,
    override val selectedStack: Int
) : MultiScreenNew, Parcelable {

    @Composable
    abstract fun Content(modo: Modo, entry: NavigationStateMultiScreenEntry)

    override fun toString() = "[$id]"
}
