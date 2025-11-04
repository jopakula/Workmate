package com.work.workmateapp.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.work.workmateapp.navigation.Graph
import com.work.workmateapp.navigation.screens.OnboardingScreens
import com.work.workmateapp.ui.screens.MainScreen
import com.work.workmateapp.ui.screens.SplashScreen

@Composable
fun RootNavigationGraph(rootNavController: NavHostController) {
    NavHost(
        navController = rootNavController,
        route = Graph.ROOT,
        startDestination = OnboardingScreens.Splash.route
    ) {
        composable(
            route = OnboardingScreens.Splash.route
        ) {
            SplashScreen(
                onSplashComplete = {
                    rootNavController.navigate(Graph.MAIN) {
                        popUpTo(Graph.ONBOARDING) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Graph.MAIN
        ) {
            MainScreen()
        }
    }
}