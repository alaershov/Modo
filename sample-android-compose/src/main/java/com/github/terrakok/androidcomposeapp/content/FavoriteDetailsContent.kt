package com.github.terrakok.androidcomposeapp.content

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.github.terrakok.androidcomposeapp.Screens

@Composable
fun FavoriteDetailsContent(screen: Screens.FavoriteDetails) {
    Text(text = "Favorite Item ${screen.name}")
}
