package com.example.petlinker.presentation.navigation

import android.app.Notification

sealed class Routes(val route: String) {

    object Main:Routes("main")
    object Home : Routes("home")
    object MyPost : Routes("my_post")
    object Inbox : Routes("inbox")
    object Profile : Routes("profile")
    object Post : Routes("post")
    object BottomNavigation : Routes("bottom_navigation")
    object Drawer : Routes("drawer")
    object AppNavigation : Routes("app_navigation")

    object PetList:Routes("petlist")
    object PetDetailScreen:Routes("petdetail")

    object AllCategory:Routes("All_Category")
    //intro
    object Splash:Routes("splash")
    object Intro:Routes("intro")
    object Login : Routes("login")
    object Signup : Routes("signup")

    //AIzaSyAPBvDMAJTH4-tg-MaAlBX96OJZFN9vCiE

}

