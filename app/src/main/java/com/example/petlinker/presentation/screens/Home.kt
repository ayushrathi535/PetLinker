package com.example.petlinker.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petlinker.R
import com.example.petlinker.data.entity.Pet
import com.example.petlinker.data.entity.PetCategory
import com.example.petlinker.domain.LocationProviderChangedReceiver
import com.example.petlinker.domain.preferences.PreferenceManager
import com.example.petlinker.domain.viewmodel.LocationViewModel
import com.example.petlinker.domain.viewmodel.PetViewModel
import com.example.petlinker.presentation.components.CardImage
import com.example.petlinker.presentation.components.Headline
import com.example.petlinker.presentation.components.PetMainCard
import com.example.petlinker.presentation.components.TxtButton
import com.example.petlinker.presentation.navigation.Routes
import com.example.petlinker.utils.HomeScreenHeader
import com.example.petlinker.utils.ResourceState
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@SuppressLint("CoroutineCreationDuringComposition", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun Home(
    navController: NavHostController,
    petViewModel: PetViewModel = hiltViewModel()
) {

    val petWithCategory by petViewModel.petWithCategory.collectAsState()

    // val navController= rememberNavController()
    val preferenceManager = PreferenceManager(LocalContext.current)
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                ),
//                title = {
//                    Text(
//                        text = "Home",
//                        style = TextStyle(
//                            fontSize = 24.sp
//                        )
//
//                    )
//                },
//                navigationIcon = {
//                    IconButton(onClick = {
////                                scope.launch {
////                                    drawerState.open()
////                                }
//                    }) {
//                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
//                    }
//                }
//
//            )
//        }
//    ) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.Gray)
        //.verticalScroll(state = scrollState)
    ) {
        val context = LocalContext.current
        val scrollState = rememberScrollState()
        val location = preferenceManager.getUserDivision()
        val userName = preferenceManager.getUserName()

        //   val locationText = location?.toString() ?: "Unknown Location"
        val userNameText = userName?.toString() ?: "Guest"



        Column() {
//modifier=Modifier.verticalScroll(state=scrollState)
//
//                val userLocation = getUserLocation(context = context)
//                val address =
            //             getReadableLocation(userLocation.latitude, userLocation.longitude, context)
//                Log.d("Address", address)
//                preferenceManager.userLocation(address)
//
//                Log.d("user locatiion--->>", preferenceManager.getUserLocation().toString())

            HomeScreenHeader(userName = userNameText, userLocation = location.toString() ?: "Null")

            CardImage(
                imageVector = R.drawable.dogadoptionposter1,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)

            )
            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Headline(text = "Category")
                Spacer(modifier = Modifier.weight(1f))
                TxtButton(text = "All Category",
                    onClick = {
                        Log.d("All Category--->","btn clickk")
                        navController.navigate(Routes.AllCategory.route)
                    }
                )
            }

            CategoryRow(navController)


            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Headline(text = "Featured Pets")
                Spacer(modifier = Modifier.weight(1f))
                TxtButton(text = "View All",
                    onClick = {
                        // TODO:  open all featured pets
                        Log.d("view All--->","btn clickk")
                        navController.navigate(Routes.AllCategory.route)
                    }
                )
            }

            Column {

                LaunchedEffect(Unit) {
                    petViewModel.getPetList()
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


}


@Composable
fun CategoryRow(navController: NavHostController) {
    val types = listOf(
        PetCategory("Dog", R.drawable.dog),
        PetCategory("Cat", R.drawable.kitty),
        PetCategory("Bird", R.drawable.bird),
        PetCategory("Cow", R.drawable.cow),
        PetCategory("Horse", R.drawable.horse),
        PetCategory("Buffalo", R.drawable.buffalo)
    )

    LazyRow(Modifier.background(Color.White)) {
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


@Composable
fun ItemRow(icon: Int, name: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .clickable(onClick = onClick),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Icon for $name",
                modifier = Modifier.size(54.dp)
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ItemRowPreview() {
    ItemRow(
        icon = R.drawable.dog,
        name = "Dog",
        onClick = {}
    )
}


@Preview(showBackground = true)
@Composable
fun RowPreview() {
    Row(
        modifier = Modifier.padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Headline(text = "Featured Pets")
        Spacer(modifier = Modifier.weight(1f))
        TxtButton(text = "View All",
            onClick = {
                // TODO:  open all featured pets
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(
        rememberNavController(),

        )
}