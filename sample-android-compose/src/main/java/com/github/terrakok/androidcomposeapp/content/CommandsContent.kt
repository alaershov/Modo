package com.github.terrakok.androidcomposeapp.content

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.terrakok.androidcomposeapp.App
import com.github.terrakok.androidcomposeapp.Screens
import com.github.terrakok.modo.*

@Composable
fun CommandsContent(
    modo: Modo,
    id: Int
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
                .weight(1.0f)
        ) {
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = {
                    modo.forward(Screens.Commands(id + 1))
                }
            ) {
                Text(text = "Forward")
            }
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = {
                    modo.replace(Screens.Commands(id + 1))
                }
            ) {
                Text(text = "Replace")
            }

            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = {
                    modo.newStack(Screens.Commands(id + 1))
                }
            ) {
                Text(text = "New root")
            }

            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = {
                    modo.backTo(Screens.Commands(3).id)
                }
            ) {
                Text(text = "Back to '3'")
            }

            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = {
                    modo.backToRoot()
                }
            ) {
                Text(text = "Back to root")
            }
        }

        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
                .weight(1.0f)
        ) {
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = {
                    modo.forward(Screens.Commands(1))
                }
            ) {
                Text(text = "Multi forward")
            }
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = {
                    modo.replace(
                        Screens.Commands(id + 1),
                        Screens.Commands(id + 2),
                        Screens.Commands(id + 3)
                    )
                }
            ) {
                Text(text = "Multi replace")
            }

            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = {
                    modo.newStack(
                        Screens.Commands(id + 1),
                        Screens.Commands(id + 2),
                        Screens.Commands(id + 3)
                    )
                }
            ) {
                Text(text = "New stack")
            }

            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = {
                    modo.back()
                }
            ) {
                Text(text = "Back")
            }

            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = {
                    modo.exit()
                }
            ) {
                Text(text = "Exit")
            }
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 640
)
@Composable
fun CommandsScreenPreview() {
    CommandsContent(App.INSTANCE.modo, 0)
}
