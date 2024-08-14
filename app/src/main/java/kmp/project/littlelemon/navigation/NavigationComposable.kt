package kmp.project.littlelemon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kmp.project.littlelemon.screens.HomeScreen
import kmp.project.littlelemon.screens.Onboarding
import kmp.project.littlelemon.screens.ProfileScreen

@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Onboarding.route) {
        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }
        composable(Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}