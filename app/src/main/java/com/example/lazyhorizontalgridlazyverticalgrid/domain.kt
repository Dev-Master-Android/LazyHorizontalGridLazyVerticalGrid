package com.example.lazyhorizontalgridlazyverticalgrid

import androidx.compose.foundation.lazy.grid.LazyGridState
import kotlinx.coroutines.delay
import kotlin.random.Random

suspend fun startScrolling(gridState: LazyGridState, images: List<Int>, onComplete: (Boolean) -> Unit) {
    for (i in 1..30) {
        // Случайно скроллим до другого элемента
        gridState.scrollToItem(Random.nextInt(images.size), 0)
        delay(100)
    }
    val hasWon = checkForMatch(images)
    onComplete(hasWon)
}

fun checkForMatch(images: List<Int>): Boolean {
    // Проверка на наличие трех одинаковых элементов по горизонтали
    for (row in 0 until images.size / 3) {
        val startIndex = row * 3
        val firstFruit = images[startIndex]
        if (firstFruit == images[startIndex + 1] && firstFruit == images[startIndex + 2]) {
            return true
        }
    }
    return false
}

fun generateRandomImages(): List<Int> {
    val imageResources = listOf(
        R.drawable.apple,
        R.drawable.banana,
        R.drawable.cherry,
        R.drawable.grape,
        R.drawable.orange,
    )
    return List(100) { imageResources[Random.nextInt(imageResources.size)] }
}