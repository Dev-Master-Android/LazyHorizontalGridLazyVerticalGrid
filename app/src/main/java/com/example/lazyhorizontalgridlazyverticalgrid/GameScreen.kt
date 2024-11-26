package com.example.lazyhorizontalgridlazyverticalgrid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun GameScreen() {
    var images by remember { mutableStateOf(generateRandomImages()) }
    var message by remember { mutableStateOf("") }
    val gridState = rememberLazyGridState()
    val scope = rememberCoroutineScope()

    Column {
        Text("Удачное совпадение", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(16.dp))
        Text(message, style = MaterialTheme.typography.bodyLarge, color = Color.Red, modifier = Modifier.padding(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = gridState,
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Top
        ) {
            items(images.size) { index ->
                ImageItem(imageRes = images[index])
            }
        }

        Button(
            onClick = {
                images = generateRandomImages()
                message = ""
                scope.launch {
                    startScrolling(gridState, images) { hasWon ->
                        message = if (hasWon) "Поздравляем! Вы выиграли!" else "Попробуйте снова!"
                    }
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Начать прокрутку")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GameScreen()
}
