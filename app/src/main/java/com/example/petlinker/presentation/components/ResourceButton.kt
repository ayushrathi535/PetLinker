package com.example.petlinker.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ResourceButton(onClick :() ->Unit,modifier: Modifier=Modifier,text:String="Submit") {


    Button(onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray,
            contentColor = Color.White))
    {

        Text(text = text,
            textAlign = TextAlign.Center)
    }

}

@Preview(showBackground = true)
@Composable
fun BtnPreview() {
    ResourceButton(onClick = { /*TODO*/ })
}