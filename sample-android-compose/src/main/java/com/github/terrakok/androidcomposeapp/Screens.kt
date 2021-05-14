package com.github.terrakok.androidcomposeapp

import android.content.Intent
import android.net.Uri
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.ExternalScreen
import com.github.terrakok.modo.android.compose.FlowComposeScreen
import com.github.terrakok.modo.android.compose.MultiComposeScreen
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

        @Composable
        override fun Content() {
            Text(text = "TODO Commands screen")
        }
    }

    @Parcelize
    class Tab(private val tabId: Int, private val i: Int) : ComposeScreen("t_$i") {

        @Composable
        override fun Content() {
            Text(text = "TODO Tab screen")
        }
    }

    fun MultiStack() = MultiComposeScreen(
        "MultiStack",
        listOf(Tab(0, 1), Tab(1, 1), Tab(2, 1)),
        1
    )

    fun FlowScreen() = FlowComposeScreen(
        "Flow",
        Start()
    )

    fun Browser(url: String) = ExternalScreen {
        Intent(Intent.ACTION_VIEW, Uri.parse(url))
    }
}
