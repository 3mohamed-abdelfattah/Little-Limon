package kmp.project.littlelemon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kmp.project.littlelemon.screens.CartScreen
import kmp.project.littlelemon.screens.HomeScreen
import kmp.project.littlelemon.screens.MenuItemDetailScreen
import kmp.project.littlelemon.screens.Onboarding
import kmp.project.littlelemon.screens.ProfileScreen
import kmp.project.littlelemon.screens.SplashScreen

@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Splash.route) {
        composable(Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }
        composable("home") { HomeScreen(navController) }
        composable("menuItemDetail/{menuItemId}") { backStackEntry ->
            val menuItemId =
                backStackEntry.arguments?.getString("menuItemId")?.toInt() ?: return@composable
            MenuItemDetailScreen(navController, menuItemId)
        }
        composable(Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(Cart.route) {
            CartScreen(navController = navController)
        }
    }
}