package com.github.terrakok.androidcomposeapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.ExternalScreen
import com.github.terrakok.modo.android.compose.FlowComposeScreen
import com.github.terrakok.modo.android.compose.MultiComposeScreen
import com.github.terrakok.modo.forward
import kotlinx.parcelize.Parcelize

object Screens {

    @Parcelize
    class Start : ComposeScreen("Start") {

        private val modo get() = App.INSTANCE.modo

        @Composable
        override fun Content() {
            StartScreen(modo)
        }
    }

    @Parcelize
    class Commands(private val i: Int) : ComposeScreen("c_$i") {

        private val modo get() = App.INSTANCE.modo

        @Composable
        override fun Content() {
            CommandsScreen(modo, i)
        }
    }

    @Parcelize
    class Tab(
        private val tabId: Int,
        private val i: Int
    ) : ComposeScreen("tab_$i") {

        @Composable
        override fun Content() {
            Text(text = "TODO Tab screen")
        }
    }

    @Parcelize
    class FavoriteList : ComposeScreen("FavoriteList") {

        private val modo get() = App.INSTANCE.modo

        @Composable
        override fun Content() {
            LazyColumn {
                items(50) { index ->
                    Text(
                        text = "Item: $index",
                        modifier = Modifier
                            .clickable {
                                modo.forward(FavoriteDetails("$index"))
                            }
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        }
    }

    @Parcelize
    class FavoriteDetails(
        private val name: String
    ) : ComposeScreen("FavoriteDetails_$name") {

        private val modo get() = App.INSTANCE.modo

        @Composable
        override fun Content() {
            Text(text = "Favorite Item $name")
        }
    }

    @Parcelize
    class Profile : ComposeScreen("Profile") {

        @Composable
        override fun Content() {
            Text(text = "Profile")
        }
    }

    fun Main() = MultiComposeScreen(
        "MainScreen",
        listOf(FavoriteList(), Profile()),
        0
    )

    fun FlowScreen() = FlowComposeScreen(
        "Flow",
        Start()
    )

    fun Browser(url: String) = ExternalScreen {
        Intent(Intent.ACTION_VIEW, Uri.parse(url))
    }
}
