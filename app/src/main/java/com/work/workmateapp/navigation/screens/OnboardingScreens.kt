package com.work.workmateapp.navigation.screens

sealed class OnboardingScreens(val route: String) {

    object Splash: OnboardingScreens("splash_screen")
    object Onboarding1: OnboardingScreens("onboarding1_screen")
    object Onboarding2: OnboardingScreens("onboarding2_screen")
    object Onboarding3: OnboardingScreens("onboarding3_screen")

}
