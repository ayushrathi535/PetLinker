package com.example.petlinker.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.petlinker.data.entity.Pet
import com.example.petlinker.domain.viewmodel.PetViewModel
import com.example.petlinker.presentation.components.PetMainCard
import com.example.petlinker.presentation.navigation.Routes
import com.example.petlinker.utils.ResourceState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetList(
    navController: NavHostController,
    petViewModel: PetViewModel = hiltViewModel(),
    node: String? = "Dog"
) {

    val petWithCategory by petViewModel.petWithCategory.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Centered Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        //TOdo: filter element to be added
                        Icon(
                            imageVector = Icons.Filled.FilterAlt,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
    ) { innerPadding ->

        Surface(Modifier.padding(innerPadding)) {

            LaunchedEffect(Unit) {
                petViewModel.getPetList(node.toString())
            }

            when (petWithCategory) {
                is ResourceState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is ResourceState.Success -> {
                    val pets = (petWithCategory as ResourceState.Success<List<Pet>>).data
                    LazyColumn {
                        items(pets.size) { pet ->
                            val pet = pets[pet]
                            PetMainCard(pet = pet) { id ->

                                navController.navigate("${Routes.PetDetailScreen.route}/${pet}")
                            }
                        }
                    }

                }

                is ResourceState.Error -> {
                    // Show error message
                    val errorMessage = (petWithCategory as ResourceState.Error).error

                }
            }


        }

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PetListPreview() {
    // PetList(name = "Dog")

}