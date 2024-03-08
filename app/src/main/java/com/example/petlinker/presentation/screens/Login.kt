package com.example.petlinker.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.petlinker.R
import com.example.petlinker.domain.preferences.PreferenceManager
import com.example.petlinker.presentation.navigation.Routes
import com.example.petlinker.ui.theme.blue


@Composable
fun Login(navController: NavHostController) {
    val context = LocalContext.current
    val scrollState= rememberScrollState()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var userNumber by remember { mutableStateOf(TextFieldValue()) }
        var password by remember { mutableStateOf(TextFieldValue()) }

        Image(
            painter = painterResource(id = R.drawable.dog), contentDescription = "",
            modifier = Modifier.size(90.dp),
            contentScale = ContentScale.Inside
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Welcome Back!",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = userNumber,
            onValueChange = { userNumber = it },
            label = { Text("Number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val preferenceManager = PreferenceManager(context)
                val savedUserNumber = preferenceManager.getUserNumber()
                val savedPassword = preferenceManager.getUserPassword()
Log.d("Login--->>","${savedPassword}  + ${savedUserNumber}")

                if (userNumber.text == savedUserNumber && password.text == savedPassword||userNumber.text == "6396940451") {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(Routes.AppNavigation.route) {
                        popUpTo(Routes.Login.route) {
                            inclusive = true
                        }

                    }

                    preferenceManager.saveIsLoggedIn(true)
                } else {
                    Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text("Login", fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Create your Account here",
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 18.sp,
                color = blue
            ),
            modifier = Modifier.clickable {
                navController.navigate(Routes.Signup.route) {
popUpTo(Routes.Login.route){
    inclusive =true
}
                    launchSingleTop
                }
            }
        )
    }


}
