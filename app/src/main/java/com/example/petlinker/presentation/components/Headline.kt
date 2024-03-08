package com.example.petlinker.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.petlinker.ui.theme.blue

@Composable
fun Headline(text: String,style: TextStyle= TextStyle(
    fontWeight = FontWeight.Medium,
    fontStyle = FontStyle.Normal,
    fontSize = 17.sp
)
) {

    Text(
        text = text,
        style = style.copy(color = Color.Black)
    )

}

@Composable
fun TxtButton(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontSize = 18.sp,
            color = blue
        ),
        modifier = Modifier.clickable(onClick = onClick) // Pass onClick lambda directly
    )
}


@Preview(showBackground = true)
@Composable
fun HeadlinePreview() {

    Headline(text = "Featured Pets")

}


@Preview(showBackground = true)
@Composable
fun ButtonPreview() {

    TxtButton(text = "View All", onClick = {

    })
}

