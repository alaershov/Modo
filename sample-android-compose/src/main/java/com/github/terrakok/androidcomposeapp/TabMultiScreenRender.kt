package com.github.terrakok.androidcomposeapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.MultiScreen
import com.github.terrakok.modo.android.compose.ModoRender
import com.github.terrakok.modo.selectStack

@Composable
fun TabMultiScreenRender(screen: MultiScreen, modo: Modo) {
    val titles = remember { listOf("Favorites", "Profile") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val tabIndex = screen.selectedStack
        val stack = remember(screen) { screen.stacks[tabIndex] }
        Box(
            modifier = Modifier
                .weight(1.0f)
                .fillMaxSize()
        ) {
            ModoRender(stack)
        }
        TabRow(selectedTabIndex = tabIndex) {
            titles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { modo.selectStack(index) }
                )
            }
        }
    }
}
