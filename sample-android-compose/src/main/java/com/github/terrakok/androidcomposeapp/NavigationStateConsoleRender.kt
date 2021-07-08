package com.github.terrakok.androidcomposeapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.terrakok.modo.NavigationState

@Composable
fun NavigationStateConsoleRender(navigationState: NavigationState) {
    Surface(color = Color.Black) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = navigationState.toString(),
            color = Color.White
        )
    }
}
