package com.work.workmateapp.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.work.workmateapp.navigation.Graph
import com.work.workmateapp.navigation.screens.OnboardingScreens
import com.work.workmateapp.ui.screens.MainScreen
import com.work.workmateapp.ui.screens.onboarding.Onboarding1Screen
import com.work.workmateapp.ui.screens.onboarding.Onboarding2Screen
import com.work.workmateapp.ui.screens.onboarding.Onboarding3Screen
import com.work.workmateapp.ui.screens.onboarding.SplashScreen

@Composable
fun RootNavigationGraph(rootNavController: NavHostController) {
    NavHost(
        navController = rootNavController,
        route = Graph.ROOT,
        startDestination = Graph.ONBOARDING
    ) {

        navigation(
            route = Graph.ONBOARDING,
            startDestination = OnboardingScreens.Splash.route
        ){
            composable(
                route = OnboardingScreens.Splash.route
            ) {
                SplashScreen(
                    onSplashComplete = {
                        rootNavController.navigate(OnboardingScreens.Onboarding1.route)
                    }
                )
            }
            composable(
                route = OnboardingScreens.Onboarding1.route
            ) {
                Onboarding1Screen(
                    onNextClick = {
                        rootNavController.navigate(OnboardingScreens.Onboarding2.route)
                    }
                )
            }
            composable(
                route = OnboardingScreens.Onboarding2.route
            ) {
                Onboarding2Screen(
                    onNextClick = {
                        rootNavController.navigate(OnboardingScreens.Onboarding3.route)
                    }
                )
            }
            composable(
                route = OnboardingScreens.Onboarding3.route
            ) {
                Onboarding3Screen(
                    onFinal = {
                        rootNavController.navigate(Graph.MAIN){
                            popUpTo(Graph.ONBOARDING) { inclusive = true }
                        }
                    }
                )
            }
        }


        composable(
            route = Graph.MAIN
        ) {
            MainScreen()
        }
    }
}