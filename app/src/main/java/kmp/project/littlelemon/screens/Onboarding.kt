package kmp.project.littlelemon.screens

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kmp.project.littlelemon.R
import kmp.project.littlelemon.navigation.Home

@Composable
fun Onboarding(navController: NavController) {

    val keyboardController = LocalSoftwareKeyboardController.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var massage by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
        firstName = sharedPreferences.getString("firstName", "") ?: ""
        lastName = sharedPreferences.getString("lastName", "") ?: ""
        email = sharedPreferences.getString("email", "") ?: ""

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty()) {
            navController.navigate(Home.route) {
                popUpTo(kmp.project.littlelemon.navigation.Onboarding.route) { inclusive = true }
            }
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        Column(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    keyboardController?.hide()
                })
            }) {

            Image(
                painter = (painterResource(id = R.drawable.logo)),
                contentDescription = "App Logo",
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 45.dp, bottom = 20.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.FillWidth
            )

            Box(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF495E57))
                    .padding(vertical = 50.dp)
            ) {
                Text(
                    text = "Let's get to know you",
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Personal Information",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(15.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = Modifier.padding(18.dp)) {

                Text(text = "First Name", fontWeight = FontWeight.W500, fontSize = 16.sp)

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            BorderStroke(2.dp, Color.LightGray),
                            RoundedCornerShape(8.dp)
                        ),
                    singleLine = true,
                    trailingIcon = {
                        if (firstName.isNotEmpty()) {
                            IconButton(onClick = { firstName = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = null)
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color(0XFF495E57)
                    ),
                    textStyle = TextStyle(fontSize = 18.sp),
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Last Name", fontWeight = FontWeight.W500, fontSize = 16.sp)

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            BorderStroke(2.dp, Color.LightGray),
                            RoundedCornerShape(8.dp)
                        ),
                    singleLine = true,
                    trailingIcon = {
                        if (lastName.isNotEmpty()) {
                            IconButton(onClick = { lastName = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = null)
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color(0XFF495E57)
                    ),
                    textStyle = TextStyle(fontSize = 18.sp),
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Email", fontWeight = FontWeight.W500, fontSize = 16.sp)

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    trailingIcon = {
                        if (email.isNotEmpty()) {
                            IconButton(onClick = { email = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = null)
                            }
                        }
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color(0XFF495E57),
                    ),
                    textStyle = TextStyle(fontSize = 18.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            BorderStroke(2.dp, Color.LightGray),
                            RoundedCornerShape(8.dp)
                        )
                )

            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                        massage = "Registration unsuccessful. Please enter all data."
                        Toast.makeText(context, massage, Toast.LENGTH_LONG).show()
                    } else {
                        saveToSharePreferences(context, firstName, lastName, email)
                        massage = "Registration successful!"
                        Toast.makeText(context, massage, Toast.LENGTH_SHORT).show()
                        navController.navigate(Home.route)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(50.dp)
                    .shadow(elevation = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(
                        0xFFF4CE14
                    )
                ),
                shape = RoundedCornerShape(25),
                border = BorderStroke(2.dp, Color(0xFFdcae59))
            ) {
                Text(
                    text = "Register",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

fun saveToSharePreferences(
    context: Context,
    firstName: String,
    lastName: String,
    email: String
) {
    val sharePreferences: SharedPreferences =
        context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    val editor = sharePreferences.edit()
    editor.putString("firstName", firstName)
    editor.putString("lastName", lastName)
    editor.putString("email", email)
    editor.apply()
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    val navController = rememberNavController()
    Onboarding(navController = navController)
}