package kmp.project.littlelemon.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kmp.project.littlelemon.R
import kmp.project.littlelemon.navigation.Home
import kmp.project.littlelemon.navigation.Onboarding
import kmp.project.littlelemon.navigation.Splash

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
        firstName = sharedPreferences.getString("firstName", "") ?: ""
        lastName = sharedPreferences.getString("lastName", "") ?: ""
        email = sharedPreferences.getString("email", "") ?: ""

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty()) {
            navController.navigate(Home.route) {
                popUpTo(Splash.route) { inclusive = true }
            }
        } else {
            navController.navigate(Onboarding.route) {
                popUpTo(Splash.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.launcher_logo),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
        )

    }
}