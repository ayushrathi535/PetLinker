package com.example.petlinker.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CardImage(imageVector: Int,modifier: Modifier = Modifier.width(80.dp)
    .height(80.dp)) {
    Column(
        Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
        ,
    ) {
        Image(
            painter = painterResource(id = imageVector),
            contentDescription = "",
            modifier = modifier,
            contentScale = ContentScale.Inside,
        )
    }

}