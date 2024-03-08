package com.example.petlinker.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petlinker.R
import com.example.petlinker.data.entity.Pet
import com.example.petlinker.data.entity.User
import com.example.petlinker.presentation.components.SpaceLine
import com.example.petlinker.presentation.components.UserCard
import com.example.petlinker.utils.DetailText
import com.example.petlinker.utils.NameHeading
import com.example.petlinker.utils.TypeValueCard

@Composable
fun PetDetailScreen(navController: NavHostController, pet: Pet) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val scrollState = rememberScrollState()
        val image = painterResource(id = R.drawable.dog_img)

        Column(
            modifier = Modifier
                .verticalScroll(state = scrollState)
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
                IconButton(onClick = { /* TODO: Handle save action */ }) {
                    Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Save")
                }
            }

            Image(
                painter = image,
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Fit
            )

            // Details section
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                NameHeading(pet = pet)

                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TypeValueCard(icon = R.drawable.age, type = "Age", value = pet.age)
                        TypeValueCard(icon = R.drawable.weight, type = "Group", value = pet.group)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TypeValueCard(icon = R.drawable.breed, type = "Breed", value = pet.breed)
                        TypeValueCard(icon = R.drawable.colour, type = "Color", value = pet.color)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TypeValueCard(
                            icon = R.drawable.category,
                            type = "Category",
                            value = pet.petCategory
                        )
                        TypeValueCard(icon = R.drawable.gender, type = "Gender", value = pet.gender)
                    }

                    Row( modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        TypeValueCard(icon =R.drawable.weight ,
                            type = "weight",
                            value = pet.weight)
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Spacer(modifier = Modifier.height(4.dp))
                DetailText(
                    text = "Behaviour Characteristics",
                    style = TextStyle(fontWeight = FontWeight.SemiBold)
                )

                Column(modifier = Modifier.padding(8.dp)) {
                    DetailText(
                        text = "Temperament",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            color = Color.Red
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        pet.behaviour.forEach { item ->
                            BehaviourCard(text = item)
                        }
                    }


                    //      BehaviourList(behaviours = pet.behaviour)

                    DetailText(
                        text = "Training",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            color = Color.Red
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    BehaviourCard(text = pet.training)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Spacer(modifier = Modifier.height(4.dp))

                DetailText(
                    text = "Address",
                    style = TextStyle(fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                DetailText(text = pet.user.location,
                    style = TextStyle(fontWeight = FontWeight.Normal,
                        fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Spacer(modifier = Modifier.height(4.dp))
                DetailText(
                    text = "Description of ${pet.petName}",
                    style = TextStyle(fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                DetailText(text = pet.description,
                    style = TextStyle(fontWeight = FontWeight.Normal,
                        fontSize = 16.sp)
                )
            }

            SpaceLine()
            DetailText(
                text = "Owner Details",
                style = TextStyle(fontWeight = FontWeight.SemiBold)
            )
            UserCard(name =pet.user.name , petName =pet.petName )
        }
    }
}


@Composable
fun BehaviourCard(text: String) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            modifier = Modifier
                .background(Color.Gray)
                .padding(8.dp),
            text = text,
            fontSize = 16.sp,
            maxLines = 1,
            color = Color.White
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TestParaa() {

    val pet = Pet(
        petName = "Maddy",
        petCategory = "Dog",
        breed = "Husky",
        gender = "Male",
        age = "2",
        group = "Adult",
        cost = "1000",
        color = "Brown",
        description = "He is a boy",
        behaviour = listOf("Protective", "caring", "childish"),
        training = "Needs Training",
  user = User("1234567890","Anmol Tower,2nd Floor, Mehrauli-Gurgaon Rd, Sukhrali, Sector 17, Gurugram, Haryana 122001, India, Gurugram","raman"),
        weight="12"
    )
    PetDetailScreen(rememberNavController(), pet)
}