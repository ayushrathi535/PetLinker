package com.example.petlinker.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Share
import androidx.compose.ui.graphics.vector.ImageVector

data class NavModel(

    val name: String,
    val routes: String,
    val filledIcon: ImageVector,
    val outlineIcon: ImageVector,


    )

data class NavItem(
    val outlineIcon: ImageVector,
    val filledIcon: ImageVector,
    val name: String
)


val drawerItem = listOf<NavItem>(

    NavItem(Icons.Outlined.Edit, Icons.Filled.Edit, "Edit Profile"),
    NavItem(Icons.Outlined.Email, Icons.Filled.Email, "Contact Us"),
    NavItem(Icons.Outlined.Feedback, Icons.Filled.Feedback, "Feedback"),
    NavItem(Icons.Outlined.Share, Icons.Filled.Share, "Share our App")

)
