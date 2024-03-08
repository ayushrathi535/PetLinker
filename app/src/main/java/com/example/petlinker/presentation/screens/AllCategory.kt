package com.example.petlinker.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petlinker.R
import com.example.petlinker.data.entity.PetCategory
import com.example.petlinker.presentation.navigation.Routes
@Composable
fun AllCategory(navController: NavHostController) {
    val types = listOf(
        PetCategory("Dog", R.drawable.dog),
        PetCategory("Cat", R.drawable.kitty),
        PetCategory("Bird", R.drawable.bird),
        PetCategory("Cow", R.drawable.cow),
        PetCategory("Horse", R.drawable.horse),
        PetCategory("Buffalo", R.drawable.buffalo),
        PetCategory("Rabbit", R.drawable.bunny),
        PetCategory("Mice", R.drawable.mouse),
        PetCategory("Owl", R.drawable.owl),
        PetCategory("Parrot", R.drawable.parrot)
    )
Surface {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(types.size) { index ->
                ItemRow(
                    icon = types[index].icon,
                    name = types[index].name,
                    onClick = {
                        navController.navigate("${Routes.PetList.route}/${types[index].name}")
                    }
                )
            }
        }
    }
}
}

@Preview
@Composable
private fun AllCatPreview() {
    AllCategory(navController = rememberNavController())
}
