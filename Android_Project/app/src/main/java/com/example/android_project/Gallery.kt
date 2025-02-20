package com.example.android_project

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController

@Composable
fun Gallery(navController: NavHostController) {
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.i("Permission: ", "Granted")
        } else {
            Log.i("Permission: ", "Denied")
        }
    }
    val context = LocalContext.current
    Column() {
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    navController.navigate(Screen.CameraScreen.route) {
                        popUpTo(Screen.GalleryScreen.route) {
                            inclusive = true
                        }
                    }
                } else {
                    requestPermissionLauncher.launch(android.Manifest.permission.CAMERA).also {
                        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            navController.navigate(Screen.CameraScreen.route) {
                                popUpTo(Screen.GalleryScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            },
        ) {
            Text("Camera")
        }
    }
}
