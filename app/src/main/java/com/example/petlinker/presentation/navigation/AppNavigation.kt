package com.example.petlinker.presentation.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PermMedia
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PermMedia
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.petlinker.data.entity.Pet
import com.example.petlinker.presentation.screens.AllCategory
import com.example.petlinker.presentation.screens.Home
import com.example.petlinker.presentation.screens.Inbox
import com.example.petlinker.presentation.screens.MyPost
import com.example.petlinker.presentation.screens.PetDetailScreen
import com.example.petlinker.presentation.screens.PetList
import com.example.petlinker.presentation.screens.Post
import com.example.petlinker.presentation.screens.Profile
import com.example.petlinker.utils.PetArgType
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@SuppressLint("SuspiciousIndentation")
@Composable
fun AppNavigation(navController1: NavHostController) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar {
                BottomBar(navController = navController)
            }
        }

    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            NavHost(navController = navController, startDestination = Routes.Main.route) {
//
//                composable(Routes.Home.route,
//                    enterTransition = {
//                        return@composable slideInHorizontally(tween(700))
//                    },
//                    exitTransition = {
//                        return@composable slideOutHorizontally(tween(700))
//                    }) {
//                    Home(navController)
//
//                }

                composable(Routes.Main.route,
                        enterTransition = {
                    return@composable slideInHorizontally(tween(700))
                },
                    exitTransition = {
                        return@composable slideOutHorizontally(tween(700))
                    }){
                    HomeScreenNavHost()
                }
                composable(Routes.Inbox.route,
                    enterTransition = {
                        return@composable slideInHorizontally(tween(700))
                    },
                    exitTransition = {
                        return@composable slideOutHorizontally(tween(700))
                    }) {
                    Inbox()
                }
                composable(Routes.Post.route,
                    enterTransition = {
                        return@composable slideInHorizontally(tween(700))
                    },
                    exitTransition = {
                        return@composable slideOutHorizontally(tween(700))
                    }) {
                    Post(navController)
                }
                composable(Routes.MyPost.route,
                    enterTransition = {
                        return@composable slideInHorizontally(tween(600))
                    },
                    exitTransition = {
                        return@composable slideOutHorizontally(tween(600))
                    }) {
                    MyPost()
                }
                composable(Routes.Profile.route,
                    enterTransition = {
                        return@composable slideInHorizontally(tween(700))
                    },
                    exitTransition = {
                        return@composable slideOutHorizontally(tween(700))
                    }) {
                    Profile(navController)
                }
//                composable(Routes.PetList.route + "/{node}",
//                    enterTransition = {
//                        return@composable slideInHorizontally(tween(700))
//                    },
//                    exitTransition = {
//                        return@composable slideOutHorizontally(tween(700))
//                    }) { navBackStackEntry ->
//
//                    val nodes = navBackStackEntry.arguments?.getString("node")
//
//                    var node = URLEncoder.encode(nodes, StandardCharsets.UTF_8.toString())
//                    node?.let { node ->
//                        PetList(navController, node = node)
//                    }
//
//                }
//
//                composable(
//                    Routes.PetDetailScreen.route + "/{pet}",
//                    arguments = listOf(navArgument("pet") {
//                        type = PetArgType()
//                    }),
//                    enterTransition = {
//                        return@composable slideInHorizontally(tween(700))
//                    },
//                    exitTransition = {
//                        return@composable slideOutHorizontally(tween(700))
//                    }
//                ) { navBackStackEntry ->
//                    val pet = navBackStackEntry.arguments?.getString("pet")
//                        ?.let { Gson().fromJson(it, Pet::class.java) }
//
//                    Log.e(" navHost to petDetail", pet.toString())
//                    pet?.let { PetDetailScreen(navController, it) }
//                }
//
//                composable(Routes.AllCategory.route,
//                    enterTransition = {
//                        return@composable slideInHorizontally(tween(700))
//                    },
//                    exitTransition = {
//                        return@composable slideOutHorizontally(tween(700))
//                    }) {
//                    AllCategory(navController)
//                }


            }

        }

    }


}


@Composable
fun HomeScreenNavHost() {
    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route){


        composable(Routes.Home.route,
            enterTransition = {
                return@composable slideInHorizontally(tween(700))
            },
            exitTransition = {
                return@composable slideOutHorizontally(tween(700))
            }) {
            Home(navController)

        }

        composable(Routes.PetList.route + "/{node}",
            enterTransition = {
                return@composable slideInHorizontally(tween(700))
            },
            exitTransition = {
                return@composable slideOutHorizontally(tween(700))
            }) { navBackStackEntry ->

            val nodes = navBackStackEntry.arguments?.getString("node")

            var node = URLEncoder.encode(nodes, StandardCharsets.UTF_8.toString())
            node?.let { node ->
                PetList(navController, node = node)
            }

        }

        composable(
            Routes.PetDetailScreen.route + "/{pet}",
            arguments = listOf(navArgument("pet") {
                type = PetArgType()
            }),
            enterTransition = {
                return@composable slideInHorizontally(tween(700))
            },
            exitTransition = {
                return@composable slideOutHorizontally(tween(700))
            }
        ) { navBackStackEntry ->
            val pet = navBackStackEntry.arguments?.getString("pet")
                ?.let { Gson().fromJson(it, Pet::class.java) }

            Log.e(" navHost to petDetail", pet.toString())
            pet?.let { PetDetailScreen(navController, it) }
        }

        composable(Routes.AllCategory.route,
            enterTransition = {
                return@composable slideInHorizontally(tween(700))
            },
            exitTransition = {
                return@composable slideOutHorizontally(tween(700))
            }) {
            AllCategory(navController)
        }


    }

}


/**
 * Function to set up bottom navigation bar.
 * @param navController The NavController to handle navigation actions.
 */

@Composable
fun BottomBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navItems = listOf(
        NavModel("Home", Routes.Main.route, Icons.Filled.Home, Icons.Outlined.Home),
        NavModel("Inbox", Routes.Inbox.route, Icons.Filled.Email, Icons.Outlined.Email),
        NavModel("Post", Routes.Post.route, Icons.Filled.Add, Icons.Outlined.Add),
        NavModel("MyPost", Routes.MyPost.route, Icons.Filled.PermMedia, Icons.Outlined.PermMedia),
        NavModel("Profile", Routes.Profile.route, Icons.Filled.Person, Icons.Outlined.Person),
    )

    BottomAppBar {
        navItems.forEach { item ->

            val isSelected = item.routes == currentRoute

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(item.routes) {
                            // Pop up to the start destination of the graph to avoid building up a large stack of destinations
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when reselecting the same item
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    if (isSelected) {
                        Icon(imageVector = item.filledIcon, contentDescription = "")
                    } else {
                        Icon(imageVector = item.outlineIcon, contentDescription = "")
                    }
                }
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun BottomnavPreview() {
    BottomBar(navController = rememberNavController())
}