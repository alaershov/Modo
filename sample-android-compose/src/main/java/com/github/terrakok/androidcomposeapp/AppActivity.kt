package com.github.terrakok.androidcomposeapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.github.terrakok.modo.android.compose.MainModoRender
import com.github.terrakok.modo.android.compose.init
import com.github.terrakok.modo.android.compose.saveState
import com.github.terrakok.modo.back

class AppActivity : AppCompatActivity() {

    private val modo get() = App.INSTANCE.modo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        modo.init(savedInstanceState, Screens.Start)

        setContent {
            MainModoRender(modo) { navigationState, content ->
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        NavigationStateConsoleRender(navigationState)
                        content()
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        modo.saveState(outState)
    }

    override fun onBackPressed() {
        modo.back()
    }
}
