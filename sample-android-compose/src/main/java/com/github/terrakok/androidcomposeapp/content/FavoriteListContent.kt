package com.github.terrakok.androidcomposeapp.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.terrakok.androidcomposeapp.Screens
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.forward

@Composable
fun FavoriteListContent(modo: Modo) {
    LazyColumn {
        items(50) { index ->
            Text(
                text = "Item: $index",
                modifier = Modifier
                    .clickable {
                        modo.forward(Screens.FavoriteDetails("$index"))
                    }
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}
