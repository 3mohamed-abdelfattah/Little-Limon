package kmp.project.littlelemon.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuItemDetailScreen(navController: NavController, menuItemId: Int) {
    // Retrieve the menu item based on the ID. Here, just a placeholder implementation.
    val menuItem = sampleMenuItems.find { it.id == menuItemId } ?: return
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    keyboardController?.hide()
                })
            }
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        TopBar(navController)
        MenuDetailScreen(menuItem = menuItem)
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuDetailScreen(menuItem: MenuItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {

            Image(
                painter = rememberAsyncImagePainter(menuItem.image),
                contentDescription = menuItem.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Column {
                Text(
                    text = menuItem.title,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = menuItem.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))


                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { /* Handle decrement */ },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(text = "-")
                    }

                    Text(text = "1")

                    Button(
                        onClick = { /* Handle increment */ },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(text = "+")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Add to cart button
                Button(
                    onClick = { /* Handle adding to cart */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(
                            0xFFF4CE14
                        )
                    ),
                    shape = RoundedCornerShape(25),
                    border = BorderStroke(1.dp, Color(0xFFdcae59))
                ) {
                    Text(
                        text = "Add for $${menuItem.price}",
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemDetailScreenPreview() {
    MenuItemDetailScreen(navController = NavController(LocalContext.current), menuItemId = 1)
}