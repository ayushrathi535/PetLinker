package com.example.petlinker.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.petlinker.presentation.screens.Login
import com.example.petlinker.presentation.screens.Signup
import com.example.petlinker.presentation.screens.SplashScreen


@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Intro.route) {

     //   mainGraph(navController)
        
        composable(Routes.AppNavigation.route){
            AppNavigation(navController1 = navController)
        }
        entryPoint(navController)
    }

}


fun NavGraphBuilder.mainGraph(navController: NavHostController) {

    navigation(startDestination = Routes.AppNavigation.route, route = Routes.Main.route) {

        composable(Routes.AppNavigation.route) {
            AppNavigation(navController)
        }
    }

}


@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.entryPoint(navController: NavHostController) {
    navigation(startDestination = Routes.Splash.route, route = Routes.Intro.route) {
        composable(
            route = Routes.Splash.route,
            enterTransition = {
                return@composable fadeIn(tween(1000))
            },
            exitTransition = {
                return@composable slideOutVertically(tween(700))
            },
            popEnterTransition = {
                return@composable slideInVertically(tween(700))
            }
        ) {
            SplashScreen(navController = navController)
        }
        composable(
            route = Routes.Signup.route,
            enterTransition = {
                return@composable slideInHorizontally(tween(700))
            },
            exitTransition = {
                return@composable slideOutHorizontally(tween(700))
            }
        ) {
            Signup(navController)
        }
        composable(
            route = Routes.Login.route,
            enterTransition = {
                return@composable slideInHorizontally(tween(700))
            },
            exitTransition = {
                return@composable slideOutHorizontally(tween(700))
            }
        ) {
            Login(navController)
        }
    }
}