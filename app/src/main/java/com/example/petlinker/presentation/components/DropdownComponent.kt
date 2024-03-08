package com.example.petlinker.presentation.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropDownCircle
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun DropDownMenuComponent(list: List<String>, onClick: (String) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown

    Column(Modifier.padding(top = 6.dp, bottom = 6.dp, start = 2.dp, end = 2.dp)) {
        TextField(
            value = selectedText,
            onValueChange = { /* restrict typing */ },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            placeholder = { Text("Select ") },
            trailingIcon = {
                Icon(
                    icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded }
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(

            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            list.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = label
                        expanded = false
                        onClick(label)
                    }
                ) {
                    Text(text = label)
                }
            }
        }
    }
}


@Composable
fun SimpleTextField(placeholder: String, onClick: (String) -> Unit,maxLine:Int=1,
                    keyboardOptions: KeyboardOptions=KeyboardOptions.Default.copy(imeAction = ImeAction.Done)) {
    var selectedText by remember { mutableStateOf("") }
    TextField(
        value = selectedText,
        onValueChange = {
            selectedText = it
            onClick(selectedText)
        },
        maxLines = maxLine, // Here, maxLine is the parameter you've provided
        placeholder = { Text(text = placeholder) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp, bottom = 6.dp, start = 2.dp, end = 2.dp),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(

        )
    )
    Log.d("PetName", "Pet Name entered: $selectedText")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SimpleTextFieldPreview() {
    SimpleTextField("enter name", onClick = {})
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DropdownPreview() {
    var selectedOption by remember { mutableStateOf("") }

    val suggestions = listOf("Kotlin", "Java", "Dart", "Python")

    DropDownMenuComponent(suggestions) { selectedValue ->

        selectedOption = selectedValue

    }

}