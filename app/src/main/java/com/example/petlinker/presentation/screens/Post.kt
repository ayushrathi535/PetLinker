package com.example.petlinker.presentation.screens

import android.Manifest
import android.util.Log
import android.view.PixelCopy.Request
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.petlinker.data.entity.Location
import com.example.petlinker.data.entity.Pet
import com.example.petlinker.data.entity.User
import com.example.petlinker.domain.preferences.PreferenceManager
import com.example.petlinker.domain.viewmodel.PetViewModel
import com.example.petlinker.presentation.components.DropDownMenuComponent
import com.example.petlinker.presentation.components.Headline
import com.example.petlinker.presentation.components.MandatoryField
import com.example.petlinker.presentation.components.MultipleCheckboxes
import com.example.petlinker.presentation.components.PetCategoryRow
import com.example.petlinker.presentation.components.ResourceButton
import com.example.petlinker.presentation.components.SimpleTextField
import com.example.petlinker.presentation.components.SingleSelectionCheckBoxGroup
import com.example.petlinker.presentation.components.SpaceLine
import com.example.petlinker.presentation.navigation.Routes
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun Post(navController: NavHostController, petViewModel: PetViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val preferenceManager = PreferenceManager(context)

    var pet_name by remember { mutableStateOf("") }
    var petCat by remember { mutableStateOf("") }
    val genderList = listOf("Male", "female")
    var gender by remember { mutableStateOf("") }
    val ageList = listOf("1", "2", "3", "4", "5", "6", "6+")
    var age by remember { mutableStateOf("") }
    var group by remember { mutableStateOf("") }
    val groupList = listOf("Adult", "Baby", "Senior", "Young")
    var cost by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }
    val checkBoxOptions = listOf("Needs Training", "Has basic Training", "Well Trained")
    var selectedOptions by remember { mutableStateOf<String>("") }
    val behaviour = listOf("Protective", "Playful", "Affectionate", "Gentle")
    var selectedOption by remember { mutableStateOf<Set<String>>(emptySet()) }
    var phone: String
    var name: String
    var location: String
    var divison:String
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text(
                        text = "Post An Ad",
                        style = TextStyle(
                            fontSize = 24.sp
                        )

                    )
                }

            )
        }
    ) {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .also { 8.dp }
        ) {
            Column(
                Modifier
                    .padding(12.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            )
            {


                // Button(onClick = {  RequestPermission(permission = Manifest.permission.CAMERA) }) {


                MandatoryField(textField = "Pet Name")
                SimpleTextField(placeholder = "Enter Name", onClick = {
                    pet_name = it
                })
                SpaceLine()
                Headline(text = "Pet Category")
                PetCategoryRow(onCategorySelected = {
                    petCat = it
                })
                MandatoryField(textField = "Breed")
                SimpleTextField(placeholder = "Enter Breed", onClick = {
                    breed = it
                })
                SpaceLine()
                MandatoryField(textField = "Gender")
                DropDownMenuComponent(list = genderList, onClick = {
                    gender = it
                })

                SpaceLine()
                Headline(text = "Behaviour")
                MandatoryField(textField = "Temperament")
                MultipleCheckboxes(checkBoxOptions = behaviour, onSelectionChange = {
                    selectedOption = it
                })
                MandatoryField(textField = "Training")
                SingleSelectionCheckBoxGroup(options = checkBoxOptions) { option ->
                    selectedOptions = option
                }

                SpaceLine()

                Headline(text = "Age & Group")

                MandatoryField(textField = "Age")

                DropDownMenuComponent(list = ageList, onClick = {
                    age = it
                })
                MandatoryField(textField = "Group")

                DropDownMenuComponent(list = groupList, onClick = {
                    group = it
                })

                SpaceLine()
                MandatoryField(textField = "Cost")
                SimpleTextField(
                    placeholder = "Enter Cost",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onClick = {
                        cost = it
                    })
                SpaceLine()
                MandatoryField(textField = "Weight")
                SimpleTextField(
                    placeholder = "Enter Weight",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onClick = { weit ->

                        weight = weit
                    })
                MandatoryField(textField = "Color")
                SimpleTextField(placeholder = "Enter Color", onClick = {
                    color = it
                })
                MandatoryField(textField = "Description")
                SimpleTextField(placeholder = "Enter Description", maxLine = 6, onClick = {
                    description = it
                })
                SpaceLine()


                ResourceButton(onClick = {

                    if (pet_name.isEmpty() || petCat.isEmpty() || breed.isEmpty() || gender.isEmpty()
                        || age.isEmpty() || group.isEmpty() || cost.isEmpty() || color.isEmpty()
                        || description.isEmpty() || selectedOption == null || selectedOptions == null
                        || weight.isEmpty()
                    ) {
                        coroutineScope.launch {
                            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT)
                                .show()
                        }


                    } else if (weight.all { it.isDigit() }) {

                        phone = preferenceManager.getUserNumber().toString()
                          name=preferenceManager.getUserName().toString()
                        location = preferenceManager.getUserLocation().toString()
                       // divison=preferenceManager.getUserDivision().toString()

                        // val user=User(phone,location, name)
                        val pet = Pet(
                            petName = pet_name,
                            petCategory = petCat,
                            breed = breed,
                            gender = gender,
                            age = age,
                            group = group,
                            cost = cost,
                            color = color,
                            description = description,
                            behaviour = selectedOption.toList(),
                            training = selectedOptions.toString(),
                          user = User(phone,location,name),
                            weight = weight
                        )

                        petViewModel.addPet(pet)

                        Log.d("pet---->>>", pet.toString())
                        Toast.makeText(
                            context,
                            "Your ad is posted Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Clear the fields after submission
                        pet_name = ""
                        petCat = ""
                        breed = ""
                        gender = ""
                        age = ""
                        group = ""
                        cost = ""
                        color = ""
                        description = ""
                        selectedOption = emptySet()
                        selectedOptions = ""
                        weight = ""

                        navController.navigate(Routes.MyPost.route)
                    } else {
                        coroutineScope.launch {
                            Toast.makeText(
                                context,
                                "weight and cost should be in  numbers",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })


                SpaceLine()

            }
        }


    }
}




