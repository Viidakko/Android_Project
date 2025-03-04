package com.example.android_project

import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController

@Composable
fun Gallery(navController: NavHostController, viewModel: PhotoViewModel) {
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
    val bitmaps by viewModel.bitmaps.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (bitmaps.isEmpty()) {
            Text("No images taken", modifier = Modifier.align(Alignment.Center
            ))
        } else {
            GalleryContent(bitmaps, modifier = Modifier.fillMaxSize())
        }
        IconButton(
            onClick = {
                if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    navController.navigate(Screen.CameraScreen.route) {
                        popUpTo(Screen.GalleryScreen.route) {
                            inclusive = true
                        }
                    }
                } else {
                    requestPermissionLauncher.launch(android.Manifest.permission.CAMERA).also {
                        navController.navigate(Screen.CameraScreen.route) {
                            popUpTo(Screen.GalleryScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(20.dp, -20.dp)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Camera,
                contentDescription = "Go to Camera",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}
