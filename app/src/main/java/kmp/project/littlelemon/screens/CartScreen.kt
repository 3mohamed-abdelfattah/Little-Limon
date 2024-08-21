package kmp.project.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kmp.project.littlelemon.R
import kmp.project.littlelemon.navigation.Home
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@Composable
fun CartScreen(navController: NavHostController) {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.US).apply {
        maximumFractionDigits = 2
        currency = Currency.getInstance("USD")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {


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

        // Delivery Time
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.van_logo),
                    contentDescription = "van logo",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Delivery time: 20 minutes",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }
            Text(
                text = "Change",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .background(Color.LightGray, RoundedCornerShape(10.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cutlery Section
        Text(
            text = "Cutlery",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Help reduce plastic waste. Only ask for cutlery if you need it",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Checkbox(checked = false, onCheckedChange = {})

        Spacer(modifier = Modifier.height(16.dp))

        // Order Summary
        Text(
            text = "Order Summary",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "1 x Bruschetta",
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = numberFormat.format(7.99),
                fontSize = 16.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add More To Your Order
        Text(
            text = "Add More To Your Order!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Example of additional item
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Greek Salad",
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = numberFormat.format(12.99),
                fontSize = 16.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Subtotal, Delivery, and Total
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Subtotal", fontSize = 16.sp, color = Color.Gray)
            Text(text = numberFormat.format(7.99), fontSize = 16.sp, color = Color.Black)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Delivery", fontSize = 16.sp, color = Color.Gray)
            Text(text = numberFormat.format(2.00), fontSize = 16.sp, color = Color.Black)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(
                text = numberFormat.format(10.99),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Checkout Button
        Button(
            onClick = { /* Handle checkout */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(25)
        ) {
            Text(
                text = "Checkout",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}