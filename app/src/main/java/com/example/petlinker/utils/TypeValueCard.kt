package com.example.petlinker.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petlinker.R
import com.example.petlinker.data.entity.Pet
import com.example.petlinker.data.entity.User

@Composable
fun TypeValueCard(icon: Int, type: String, value: String) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Transparent),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp),

        ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = icon), contentDescription = "",
                modifier = Modifier.size(38.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))

            Column(Modifier.padding(end = 20.dp)
                .width(70.dp)) {
                
                DetailTextTypeValue(
                    text = type,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray
                    )
                )
                DetailTextTypeValue(
                    text = value,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                )
            }
        }
    }
}


@Composable
fun NameHeading(pet: Pet) {
    Row(modifier= Modifier
        .padding(8.dp)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        
        DetailText(text = pet.petName, style = TextStyle(fontWeight = FontWeight.Bold))
        DetailText(text = "$"+pet.cost, style = TextStyle(Color.Magenta))
    }
    
}


@Composable
fun DetailText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp,
        color = Color.Black
    )
) {
    Column {
        Text(
            text = text,
            style = style.copy(
                fontSize = 20.sp,
                fontWeight = style.fontWeight,
                color = style.color,

            ),
            textAlign = TextAlign.Center
        )
    }
}





@Composable
fun DetailTextTypeValue(text:String,modifier:Modifier=Modifier,style: TextStyle= TextStyle.Default.copy(fontWeight = FontWeight.SemiBold,
    color = Color.Black)) {
    Column {
        Text(
            text = text,
            style = style.copy(
                fontWeight = style.fontWeight,
                color = style.color,
            )
            , textAlign = TextAlign.Center
        )
    }




}

@Preview(showBackground = true)
@Composable
fun NameHeadingPreview() {

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
        user = User("1234567890","gurgaon","raman"),
        weight="12"
    )
    NameHeading(pet=pet)
}


@Preview(showBackground = true)
@Composable
fun CardPreview() {

Column {
    Row {
        TypeValueCard(icon = R.drawable.weight, type = "breed", value = "labra")
        TypeValueCard(icon = R.drawable.weight, type = "breed", value = "labra")
    }

    Row {
        TypeValueCard(icon = R.drawable.weight, type = "description", value = "labra")
        TypeValueCard(icon = R.drawable.weight, type = "breed", value = "labra")
    }
    Row {
        TypeValueCard(icon = R.drawable.weight, type = "breed", value = "labra")
        TypeValueCard(icon = R.drawable.weight, type = "breed", value = "labra")
    }
}

}


@Preview
@Composable
fun PreviewType() {
    TypeValueCard(icon = R.drawable.weight, type = "breed", value = "labra")
}