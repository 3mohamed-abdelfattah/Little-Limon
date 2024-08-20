package kmp.project.littlelemon.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import kmp.project.littlelemon.R
import kmp.project.littlelemon.navigation.Home
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

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
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    navController.navigate(Home.route) {
                        popUpTo("Home") { inclusive = true }
                    }
                },
                modifier = Modifier.align(Alignment.CenterStart)
            ) { Icon(Icons.AutoMirrored.Sharp.ArrowBack, contentDescription = "Back") }
            TopBar(navController)
        }
        MenuDetailScreen(menuItem = menuItem)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuDetailScreen(menuItem: MenuItem) {
    val quantity = remember { mutableIntStateOf(1) }
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.US).apply {
        maximumFractionDigits = 2
        currency = Currency.getInstance("USD")
    }

    val options = listOf("Feta" to 1.00, "Parmesan" to 1.00, "Dressing" to 1.00)
    val checkedStates = remember { mutableStateOf(options.map { false }) }
    val extraCost = remember { mutableStateOf(0.0) }

    fun updateExtraCost() {
        extraCost.value = checkedStates.value
            .mapIndexed { index, checked -> if (checked) options[index].second else 0.0 }
            .sum()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {

            Image(
                painter = rememberAsyncImagePainter(menuItem.image),
                contentDescription = menuItem.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Column {
                Text(
                    text = menuItem.title,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = menuItem.description,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Delivery time section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.van_logo),
                        contentDescription = "van logo",
                        modifier = Modifier.size(30.dp),
                        Color.DarkGray
                    )
                    Spacer(modifier = Modifier.widthIn(10.dp))
                    Text(
                        text = "Delivery time: 20 minutes",
                        color = Color.DarkGray,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.widthIn(10.dp))

                    Text(
                        text = "Change",
                        color = Color(0xFF78817d),
                        fontWeight = FontWeight.W900,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .background(Color(0XFFe5eae8), RoundedCornerShape(10.dp))
                            .padding(10.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Add-on options
                Text(
                    text = "Add", fontWeight = FontWeight.W900, fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.height(15.dp))

                options.forEachIndexed { index, option ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = option.first,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$${option.second}",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                            Checkbox(
                                checked = checkedStates.value[index],
                                onCheckedChange = { checked ->
                                    val newCheckedStates = checkedStates.value.toMutableList()
                                    newCheckedStates[index] = checked
                                    checkedStates.value = newCheckedStates
                                    updateExtraCost()
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(0xFF4B5E58),
                                    uncheckedColor = Color(0xFF4B5E58),
                                    checkmarkColor = Color.White
                                )
                            )
                        }
                    }
                    HorizontalDivider(
                        color = Color(0XFFE7E7E7),
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Quantity selector
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { if (quantity.intValue > 1) quantity.intValue-- },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                    ) {
                        Text(text = "-", color = Color.DarkGray, fontSize = 20.sp)
                    }

                    Text(
                        text = quantity.intValue.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp
                    )

                    Button(
                        onClick = { quantity.intValue++ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                    ) {
                        Text(text = "+", color = Color.DarkGray, fontSize = 20.sp)
                    }
                }

                // Add to cart button
                Button(
                    onClick = { /* Handle adding to cart */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF4CE14)
                    ),
                    shape = RoundedCornerShape(25),
                    border = BorderStroke(1.dp, Color(0xFFdcae59))
                ) {
                    val totalPrice =
                        (menuItem.price.toDouble() + extraCost.value) * quantity.intValue
                    Text(
                        text = "Add for ${numberFormat.format(totalPrice)}",
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold, fontSize = 18.sp
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
