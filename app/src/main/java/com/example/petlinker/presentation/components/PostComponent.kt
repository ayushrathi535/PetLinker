package com.example.petlinker.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petlinker.ui.theme.red_color

@Composable
fun PetCategoryRow(onCategorySelected: (String) -> Unit) {
    val petList = listOf<String>("Dog", "Cat", "Bird", "Cow", "Rabbit", "Buffalo")
    var selectedCategoryIndex by remember { mutableStateOf(-1) }

    LazyRow(
        modifier = Modifier
            .padding(top = 6.dp, bottom = 6.dp, start = 2.dp, end = 2.dp)
    ) {
        items(petList.size) { index ->
            PetCategoryName(
                name = petList[index],
                onClick = { selectedCategoryIndex = index },
                isSelected = index == selectedCategoryIndex
            )
            if (index == selectedCategoryIndex) {
                onCategorySelected(petList[index])
            }
        }
    }
}

@Composable
fun PetCategoryName(name: String, onClick: () -> Unit, isSelected: Boolean) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .wrapContentSize()
            .clickable(onClick = onClick),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        //backgroundColor = if (isSelected) Color.Red else Color.White
    ) {
        Text(
            modifier = Modifier
                .background(if (isSelected) Color.Red else Color.White)
                .padding(12.dp),
            text = name,
            fontSize = 16.sp,
            maxLines = 1,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}


@Composable
fun MandatoryField(textField: String) {

    //  val text= Text(text = textField, fontSize = 18.sp, color = Color.Black)
    val annotatedText = buildAnnotatedString {
        append(textField)
        pushStyle(SpanStyle(color = red_color, fontSize = 18.sp))
        append("* ")
        pop()
    }

    Text(
        text = annotatedText, fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )
}


@Composable
fun SpaceLine() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 1.dp)
            .background(color = Color.Gray)
    )
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 12.dp)
    )
}

@Composable
fun CheckboxWithTextAndCallback(
    text: String,
    onCheckedChange: (String, Boolean) -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
                onCheckedChange(
                    text,
                    isChecked
                ) // Pass both the text and the checked state to the callback
            },
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun MultipleCheckboxes(
    checkBoxOptions: List<String>,
    onSelectionChange: (Set<String>) -> Unit
) {
    val selectedOptions = remember { mutableSetOf<String>() }

    Column {
        checkBoxOptions.forEach { option ->
            CheckboxWithTextAndCallback(
                text = option,
                onCheckedChange = { checkedText, isChecked ->
                    if (isChecked) {
                        selectedOptions.add(checkedText)
                    } else {
                        selectedOptions.remove(checkedText)
                    }
                    onSelectionChange(selectedOptions)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CheckboxPreview() {

    val behaviour = listOf("Protective", "Playful", "Affectionate", "Gentle")
    var selectedOption: Set<String> by remember { mutableStateOf(emptySet()) }

    MultipleCheckboxes(checkBoxOptions = behaviour, onSelectionChange = {
        selectedOption = it
    })

}


@Preview(showBackground = true)
@Composable
fun PetCategoryRowPreview() {
    PetCategoryRow(onCategorySelected = {})
}

@Preview(showBackground = true)
@Composable
fun PetCategoryNamePreview() {
    PetCategoryName(
        name = "Dog",
        onClick = { },
        isSelected = false
    )
}


@Preview(showBackground = true)
@Composable
fun MandatoryPreview() {
    MandatoryField(textField = "something")

}


@Composable
fun SingleSelectionCheckBoxGroup(
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var selectedOption by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        options.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = selectedOption == option,
                    onCheckedChange = { isChecked ->
                        if (isChecked) {
                            selectedOption = option
                            onOptionSelected(option)
                        } else {
                            selectedOption = ""
                        }
                    }
                )
                Text(
                    text = option,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun MyScreen() {
    var selectedOption by remember { mutableStateOf("") }

    val options = listOf("Option 1", "Option 2", "Option 3")

    SingleSelectionCheckBoxGroup(options = options) { option ->
        selectedOption = option
    }

}

@Preview
@Composable
fun PreviewMyScreen() {
    MyScreen()
}
