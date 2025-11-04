package com.work.workmateapp.navigation.screens

sealed class MainScreens(val route: String) {

    object Main: MainScreens("splash_screen")
    object Detail: MainScreens("detail_screen")

}
