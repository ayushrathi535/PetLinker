package com.example.petlinker.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petlinker.R
import com.example.petlinker.data.entity.Pet
import com.example.petlinker.data.entity.User
import com.example.petlinker.ui.theme.blue
import com.example.petlinker.ui.theme.pink_color
import com.example.petlinker.ui.theme.red_color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetMainCard(pet: Pet, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = { onClick(pet.id.toString()) }
    ) {
            Row(
            modifier = Modifier
                .background(Color.White)
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CardImage(imageVector = R.drawable.dog_img, modifier = Modifier.size(100.dp))
            PetInfoDetails(pet = pet)
            Spacer(modifier = Modifier.weight(1f))
                Column(modifier = Modifier.align(Alignment.Top)) {
                    SaveIcon(onClick = {  /* Handle save icon click */ })
                }
        }


    }

    val logMessage = """
        Pet Name: ${pet.petName}
        Pet Category: ${pet.petCategory}
        Breed: ${pet.breed}
        Gender: ${pet.gender}
        Age: ${pet.age}
        Group: ${pet.group}
        Cost: ${pet.cost}
        Color: ${pet.color}
        Description: ${pet.description}
        Behaviours: ${pet.behaviour.joinToString()}
        Training: ${pet.training}
        Weight: ${pet.weight}
        User number: ${pet.user.mobileNumber}
        User Address: ${pet.user.location}
        User Name: ${pet.user.name}
    """.trimIndent()

    Log.d("Pet Main Card ", logMessage)
}



@Composable
fun PetInfoDetails(pet: Pet) {
    Column(
        modifier = Modifier
            .padding(6.dp)
            .background(Color.White)
    ) {
        val cityAndState = extractCityAndState(pet.user.location)

        val city=cityAndState?.first ?:"City"
        val state=cityAndState?.second?:"State"

        Spacer(modifier = Modifier.height(2.dp))
        PetName(name = pet.petName, gender = pet.gender)
        Breed(breed = pet.breed)
        Location(city = city, state = state)
        PriceTag(price = pet.cost)

    }
}

@Composable
fun Breed(breed: String) {
    Text(
        text = breed,
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 19.sp,
            color = Color.Gray
        )
    )
}

@Composable
fun Location(city: String, state: String) {
    Text(
        text = "$city, $state",
        modifier = Modifier,
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            fontSize = 18.sp
        ),
        maxLines = 2,
overflow = TextOverflow.Ellipsis
    )
}


fun extractCityAndState(address: String): Pair<String, String>? {
    val parts = address.split(",").map { it.trim() }
    val city = parts.getOrNull(parts.size - 2) // Assuming city is second to last part
    val state = parts.lastOrNull() // Assuming state is the last part

    return if (city != null && state != null) {
        Pair(city, state)
    } else {
        null
    }
}


@Composable
fun PetName(name: String, gender: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = name,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier.width(3.dp))
        Icon(
            imageVector = if (gender.equals("male", ignoreCase = true)) {
                Icons.Filled.Male
            } else {
                Icons.Filled.Female
            },
            contentDescription = null,
            modifier = Modifier.size(22.dp)
        )
    }
}

@Composable
fun PriceTag(price: String) {
    val annotatedString = buildAnnotatedString {
        appendInlineContent("attach", "\uD83D\uDCB0") // Unicode for paperclip icon
        append(" $price") // Append the price
    }

    Text(
        text = annotatedString,
        color = Color.Magenta,
        fontSize = 18.sp
    )
}

@Composable
fun SaveIcon(onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Outlined.FavoriteBorder,
        contentDescription = "save",
        tint = Color.Red,
        modifier = Modifier
            .padding(6.dp)
            .clickable(onClick = onClick)
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PetMainPreview() {
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
        weight = "1",
        user = User("6456454454","Anmol Tower,2nd Floor, Mehrauli-Gurgaon Rd, Sukhrali, Sector 17, Gurugram, Haryana 122001, India, Gurugram","ayush"
    )
    )
    PetMainCard(pet = pet, onClick = {})

}

@Preview
@Composable
fun CardImagePreview() {
    CardImage(imageVector = R.drawable.dog_img)

}


@Preview
@Composable
fun PetNamePreview() {

    PetName(name = "Meddy", gender = "Male")
}


@Preview
@Composable
fun SavePreview() {
    SaveIcon {

    }
}


@Preview
@Composable
fun PreviewPriceTag() {
    PriceTag("19.99")
}

@Preview
@Composable
fun BreedPreview() {
    Breed(breed = "Labra")
}

@Preview
@Composable
fun LocationPreview() {
    Location(city = "Bijnor", state = "UP")
}


@Preview
@Composable
fun PetInfoPreview() {
  //  PetInfoDetails()
}