package com.github.terrakok.androidcomposeapp.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.terrakok.androidcomposeapp.App
import com.github.terrakok.androidcomposeapp.Screens
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.android.compose.launch
import com.github.terrakok.modo.forward

@Composable
fun StartContent(modo: Modo) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            onClick = {
                modo.forward(Screens.Commands(1))
            }
        ) {
            Text(text = "Navigation commands")
        }
        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            onClick = {
                modo.forward(Screens.Main)
            }
        ) {
            Text(text = "Multistack navigation")
        }

        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            onClick = {
                modo.launch(Screens.Browser("https://github.com/terrakok/Modo"))
            }
        ) {
            Text(text = "GitHub page")
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 640
)
@Composable
fun StartContentPreview() {
    StartContent(App.INSTANCE.modo)
}
