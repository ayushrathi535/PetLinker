package com.example.petlinker.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.petlinker.R
import com.example.petlinker.domain.preferences.PreferenceManager
import com.example.petlinker.presentation.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val context = LocalContext.current

    val preferenceManager = PreferenceManager(context)
   // val isLoggedIn = preferenceManager.getUserName() != null && preferenceManager.getUserPassword() != null

    LaunchedEffect(Unit) {
        delay(2000) // Splash screen delay for 2 seconds
        if (preferenceManager.getIsLoggedIn()) {
            navController.navigate(Routes.AppNavigation.route){
            popUpTo(Routes.Splash.route) {
                inclusive = true
            }
        }
        } else {
            navController.navigate(Routes.Signup.route) {
                popUpTo(Routes.Splash.route) {
                    inclusive = true
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
       //     .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(id = R.drawable.dog_img),
                contentDescription = "Dog Image",
                modifier = Modifier.size(300.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Welcome Petty", style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center
            ))
        }

    }
}
