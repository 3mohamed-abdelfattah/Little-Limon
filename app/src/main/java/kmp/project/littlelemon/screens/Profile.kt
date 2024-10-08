package kmp.project.littlelemon.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kmp.project.littlelemon.R
import kmp.project.littlelemon.navigation.Home
import kmp.project.littlelemon.navigation.Profile

@Composable
fun ProfileScreen(navController: NavController) {

    val keyboardController = LocalSoftwareKeyboardController.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    val checkedStatesOne = remember { mutableStateOf(true) }
    val checkedStatesTwo = remember { mutableStateOf(false) }
    val checkedStatesThree = remember { mutableStateOf(true) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
        firstName = sharedPreferences.getString("firstName", "") ?: ""
        lastName = sharedPreferences.getString("lastName", "") ?: ""
        email = sharedPreferences.getString("email", "") ?: ""
        number = sharedPreferences.getString("number", "") ?: ""
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    keyboardController?.hide()
                })
            }) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                IconButton(onClick = {
                    navController.navigate(Home.route) {
                        popUpTo("Home") { inclusive = true }
                    }
                }) { Icon(Icons.AutoMirrored.Sharp.ArrowBack, contentDescription = "Back") }

                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = (painterResource(id = R.drawable.logo)),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .padding(vertical = 30.dp, horizontal = 50.dp)
                            .align(Alignment.Center)
                            .width(200.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(BorderStroke(2.dp, Color(0xFFEDEFEF)), CircleShape)
                    .shadow(elevation = 15.dp, shape = CircleShape, clip = true)
            ) {
                // Image
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                // Edit Icon
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(BorderStroke(1.dp, Color.Gray))
                        .clickable {
                            // Handle edit action here
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Profile Picture",
                        tint = Color.Gray,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
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
                    onValueChange = { newValue ->
                        firstName = newValue
                        saveToSharePreferences(context, "firstName", newValue)
                    },
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
                    onValueChange = { newValue ->
                        lastName = newValue
                        saveToSharePreferences(context, "lastName", newValue)
                    },
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
                    onValueChange = { newValue ->
                        email = newValue
                        saveToSharePreferences(context, "email", newValue)
                    },
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

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Phone Number", fontWeight = FontWeight.W500, fontSize = 16.sp)

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = number,
                    onValueChange = { newValue ->
                        number = newValue
                        saveToSharePreferences(context, "number", newValue)
                    },
                    trailingIcon = {
                        if (number.isNotEmpty()) {
                            IconButton(onClick = { number = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = null)
                            }
                        }
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color(0XFF495E57),
                    ),
                    textStyle = TextStyle(fontSize = 18.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            BorderStroke(2.dp, Color.LightGray),
                            RoundedCornerShape(8.dp)
                        )
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Email notification", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Checkbox(
                        checked = checkedStatesOne.value,
                        onCheckedChange = { checked -> checkedStatesOne.value = checked },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF4B5E58),
                            uncheckedColor = Color(0xFF4B5E58),
                            checkmarkColor = Color.White
                        )
                    )
                    Text(
                        text = "Order statues",
                        fontSize = 14.sp
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Checkbox(
                        checked = checkedStatesTwo.value,
                        onCheckedChange = { checked -> checkedStatesTwo.value = checked },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF4B5E58),
                            uncheckedColor = Color(0xFF4B5E58),
                            checkmarkColor = Color.White
                        )
                    )
                    Text(
                        text = "Password changes",
                        fontSize = 14.sp
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Checkbox(
                        checked = checkedStatesThree.value,
                        onCheckedChange = { checked -> checkedStatesThree.value = checked },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF4B5E58),
                            uncheckedColor = Color(0xFF4B5E58),
                            checkmarkColor = Color.White
                        )
                    )
                    Text(
                        text = "Special offers",
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    clearSharedPreferences(context)
                    navController.navigate(kmp.project.littlelemon.navigation.Onboarding.route) {
                        popUpTo(Profile.route) { inclusive = true }
                        popUpTo(Home.route) { inclusive = true }
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
                    text = "Log Out",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

fun saveToSharePreferences(context: Context, key: String, value: String) {
    val sharePreferences: SharedPreferences =
        context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    with(sharePreferences.edit()) {
        putString(key, value)
        apply()
    }
}

fun clearSharedPreferences(context: Context) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.clear()
    editor.apply()
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}