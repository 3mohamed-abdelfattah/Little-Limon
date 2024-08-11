package kmp.project.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNavigation()
        }
    }


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
}