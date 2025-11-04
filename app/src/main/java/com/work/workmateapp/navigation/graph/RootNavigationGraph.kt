package com.work.workmateapp.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.work.workmateapp.navigation.Graph
import com.work.workmateapp.navigation.screens.MainScreens
import com.work.workmateapp.ui.screens.main.DetailScreen
import com.work.workmateapp.ui.screens.main.MainScreen

@Composable
fun RootNavigationGraph(rootNavController: NavHostController) {
    NavHost(
        navController = rootNavController,
        route = Graph.ROOT,
        startDestination = Graph.ONBOARDING
    ) {

        onboardingGraph(rootNavController = rootNavController)

        navigation(
            route = Graph.MAIN,
            startDestination = MainScreens.Main.route
        ){
            composable(
                route = MainScreens.Main.route
            ) {
                MainScreen(
                    onClick = {
                        rootNavController.navigate(MainScreens.Detail.route)
                    }
                )
            }

            composable(
                route = MainScreens.Detail.route
            ) {
                DetailScreen()
            }
        }

    }
}