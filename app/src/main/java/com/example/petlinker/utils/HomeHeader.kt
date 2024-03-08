package com.example.petlinker.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenHeader(userName: String, userLocation: String) {
    TopAppBar(
        backgroundColor = Color.Gray,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = userLocation,
                    style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onPrimary),
                    textAlign = TextAlign.Start
                )
            }
            Text(
                text = "Welcome, $userName",
                style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onPrimary),
                textAlign = TextAlign.End
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeHeaderpreview() {
    HomeScreenHeader(userName = "Ayush", userLocation ="Gurgaon" )
}