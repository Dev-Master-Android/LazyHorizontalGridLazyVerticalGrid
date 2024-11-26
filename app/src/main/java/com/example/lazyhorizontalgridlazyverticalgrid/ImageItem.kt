package com.example.lazyhorizontalgridlazyverticalgrid

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ImageItem(imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier
            .size(100.dp)
            .clickable { }
    )
}
@Preview(showBackground = true)
@Composable
fun ImageItemPreview() {
    ImageItem(imageRes = R.drawable.apple)
}
