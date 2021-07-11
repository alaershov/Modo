package com.github.terrakok.androidcomposeapp

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import com.github.terrakok.androidcomposeapp.content.*
import com.github.terrakok.modo.*
import com.github.terrakok.modo.android.compose.ComposeMultiScreen
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.ExternalScreen
import kotlinx.parcelize.Parcelize

object Screens {

    @Parcelize
    object FavoriteList : ComposeScreen("FavoriteList") {

        @Composable
        override fun Content(modo: Modo) {
            FavoriteListContent(modo)
        }
    }

    @Parcelize
    class FavoriteDetails(
        val name: String
    ) : ComposeScreen("FavoriteDetails_$name") {

        @Composable
        override fun Content(modo: Modo) {
            FavoriteDetailsContent(this)
        }
    }


    @Parcelize
    object Profile : ComposeScreen("Profile") {

        @Composable
        override fun Content(modo: Modo) {
            ProfileContent()
        }
    }

    @Parcelize
    object Main : ComposeMultiScreen(
        id = "MainScreen",
        stacks = listOf(
            NavigationState(listOf(NavigationStateScreenEntry(FavoriteList))),
            NavigationState(listOf(NavigationStateScreenEntry(Profile)))
        ),
        selectedStack = 0
    ) {

        @Composable
        override fun Content(modo: Modo, entry: NavigationStateMultiScreenEntry) {
            MainContent(modo, entry)
        }
    }

    @Parcelize
    class Commands(val i: Int) : ComposeScreen("ItemScreen_$i") {

        @Composable
        override fun Content(modo: Modo) {
            CommandsContent(modo, i)
        }
    }

    @Parcelize
    class Sample(val i: Int) : ComposeScreen("ItemScreen_$i") {

        @Composable
        override fun Content(modo: Modo) {
            SampleContent(modo, this)
        }
    }

    @Parcelize
    object Start : ComposeScreen("Start") {

        @Composable
        override fun Content(modo: Modo) {
            StartContent(modo)
        }
    }

    fun Browser(url: String) = ExternalScreen {
        Intent(Intent.ACTION_VIEW, Uri.parse(url))
    }
}
