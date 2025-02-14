package com.example.android_project

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.CameraScreen.route) {
        composable(route = Screen.CameraScreen.route) {
            Camera(navController = navController)
        }
        composable(route = Screen.GalleryScreen.route) {
            Gallery(navController = navController)
        }
    }
}

