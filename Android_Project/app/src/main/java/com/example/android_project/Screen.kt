package com.example.android_project

sealed class Screen(val route: String) {
    data object CameraScreen: Screen("camera_screen")
    data object GalleryScreen: Screen("gallery_screen")
}
