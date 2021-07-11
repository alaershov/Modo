package com.github.terrakok.androidcomposeapp.content

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
import com.github.terrakok.modo.NavigationStateMultiScreenEntry
import com.github.terrakok.modo.android.compose.ModoRender
import com.github.terrakok.modo.selectStack

@Composable
fun MainContent(
    modo: Modo,
    multiScreenEntry: NavigationStateMultiScreenEntry
) {
    val titles = remember { listOf("Favorites", "Profile") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val tabIndex = multiScreenEntry.selectedStack
        val selectedNavigationState = remember(multiScreenEntry) { multiScreenEntry.stacks[tabIndex] }
        Box(
            modifier = Modifier
                .weight(1.0f)
                .fillMaxSize()
        ) {
            ModoRender(modo, selectedNavigationState) { _, content ->
                content()
            }
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
