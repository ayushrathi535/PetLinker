package com.example.petlinker.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.petlinker.R
import com.example.petlinker.domain.preferences.PreferenceManager
import com.example.petlinker.presentation.navigation.Routes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.petlinker.presentation.components.SpaceLine


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Profile",
                        style = TextStyle(
                            fontSize = 24.sp
                        )

                    )
                })
        }
    ) {

        val context = LocalContext.current
        var preferenceManager = PreferenceManager(context)
        val scroll = rememberScrollState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .verticalScroll(scroll)
        ) {

            ProfileImageCard(imageResId = R.drawable.dog_img) {

            }
            SpaceLine()


            Spacer(modifier = Modifier.height(6.dp))
            var text = preferenceManager.getUserName() ?: "NULL"
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var showDialog by remember {
                    mutableStateOf(false)
                }
                //   Log.d("name--->>>", name.toString())

                ProfileCard(imageVector = Icons.Filled.Person, name = "Profile") {

                }

                ProfileCard(imageVector = Icons.Filled.Policy, name = "Policy") {

                }
                ProfileCard(imageVector = Icons.Filled.Password, name = "Reset password") {

                }

                SpaceLine()

                ProfileCard(imageVector = Icons.Filled.Share, name = "Share App") {

                }
                ProfileCard(imageVector = Icons.Filled.ExitToApp, name = "Logout") {
                    showDialog = true
                }

                if (showDialog) {
                    AlertDialogSample(
                        openDialog = showDialog,
                        onDismiss = { showDialog = false },
                        onConfirm = {
                            preferenceManager.clearUserData()
                            navController.navigate(Routes.Signup.route)
                            {
                                popUpTo(Routes.Signup.route)
                            }
                        }
                    )
                }
            }

        }


    }
}

@Composable
fun ProfileImageCard(
    modifier: Modifier = Modifier,
    imageResId: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(16.dp),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
            Icon(
                imageVector = Icons.Default.CameraAlt,
                contentDescription = "Edit Profile",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
                    .clickable(onClick = onClick)
            )
        }
    }
}

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    name: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}


@Composable
fun AlertDialogSample(
    openDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "Alert Dialog")
            },
            text = {
                Text("Select yes to Exit ")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    }
                ) {
                    Text("YES")
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss
                ) {
                    Text("NO")
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AlertPreview() {
    var showDialog by remember { mutableStateOf(false) }

    AlertDialogSample(
        openDialog = showDialog,
        onDismiss = { showDialog = false },
        onConfirm = {
            Log.d("logout preview--->>>>", "logout success")
        }
    )

}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(navController = rememberNavController())
}

