package com.work.workmateapp.navigation.screens

sealed class OnboardingScreens(val route: String) {

    object Splash: OnboardingScreens("splash_screen")

}
