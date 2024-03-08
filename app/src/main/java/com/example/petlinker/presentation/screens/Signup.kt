package com.example.petlinker.presentation.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petlinker.R
import com.example.petlinker.domain.preferences.PreferenceManager
import com.example.petlinker.presentation.navigation.Routes
import com.example.petlinker.ui.theme.blue


@Composable
fun Signup(navController: NavHostController) {
    val context = LocalContext.current

    val scrollState= rememberScrollState()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize().verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var name by remember { mutableStateOf(TextFieldValue()) }
        var phoneNumber by remember { mutableStateOf(TextFieldValue()) }
        var password by remember { mutableStateOf(TextFieldValue()) }
        Image(
            painter = painterResource(id = R.drawable.dog), contentDescription = "",
            modifier = Modifier.size(90.dp),
            contentScale = ContentScale.Inside
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Create Your Account",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),

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
                if (name.text.isNotEmpty() && phoneNumber.text.isNotEmpty() && password.text.isNotEmpty()) {
                    if (phoneNumber.text.length == 10) {
                        if (saveUserData(context, name.text, phoneNumber.text, password.text)) {

                            Log.d("Signup--->>>", "${name}  ${phoneNumber}  ${password}")

                            navController.navigate(Routes.Login.route){
                                popUpTo(Routes.Signup.route){
                                    inclusive=true

                                }
                                launchSingleTop
                            }


                        } else {
                            Toast.makeText(context, "Failed to save user data", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Phone number length should be 10",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text("Sign Up", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Already Account?Login here",
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 18.sp,
                color = blue
            ),
            modifier = Modifier.clickable {
                navController.navigate(Routes.Login.route){
                    popUpTo(Routes.Signup.route){
                        inclusive=true
                    }
                    launchSingleTop
                }
            }
        )

    }
}


fun saveUserData(context: Context, name: String, phoneNumber: String, password: String): Boolean {
    val preferenceManager = PreferenceManager(context)
    return try {
        preferenceManager.saveUserData(name, phoneNumber, password)
        true
    } catch (e: Exception) {
        false
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignupPreview() {

    Signup(rememberNavController())
}