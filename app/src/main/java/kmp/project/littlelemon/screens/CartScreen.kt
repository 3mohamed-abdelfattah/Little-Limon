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
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
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
            .padding(15.dp)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
                color = Color(0xFF78817d),
                fontWeight = FontWeight.W900,
                fontSize = 16.sp,
                modifier = Modifier
                    .background(Color(0XFFe5eae8), RoundedCornerShape(25.dp))
                    .padding(10.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider(
            color = Color(0XFFE7E7E7),
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Cutlery Section
        Text(
            text = "Cutlery",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Help reduce plastic waste.\nOnly ask for cutlery if you need it",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Checkbox(
                checked = true, onCheckedChange = {}, colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF4B5E58),
                    uncheckedColor = Color(0xFF4B5E58),
                    checkmarkColor = Color.White
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Order Summary
        Text(
            text = "Order Summary",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .background(Color(0XFFEAEAEA))
                .padding(6.dp)
        ) {
            Text(text = "items", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "   1 x Bruschetta",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = numberFormat.format(5.99),
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "   2 x Espresso",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = numberFormat.format(11.99),
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "   1 x Lemon Desert",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = numberFormat.format(7.99),
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add More To Your Order
        Text(
            text = "Add More To Your Order!",
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
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
            Column {
                Text(
                    text = "Greek Salad",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold

                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "The famous greek salad of crispy lettuce,\npeppers, olives, our Chicago.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = numberFormat.format(12.99),
                    fontSize = 16.sp,
                    color = Color(0XFF4B5E58),
                    fontWeight = FontWeight.Bold
                )
            }
            SubcomposeAsyncImage(
                model = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Subtotal, Delivery, and Total
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Subtotal", fontSize = 16.sp, color = Color.Gray)
            Text(text = numberFormat.format(25.99), fontSize = 16.sp, color = Color.Black)
        }
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Delivery", fontSize = 16.sp, color = Color.Gray)
            Text(text = numberFormat.format(2.00), fontSize = 16.sp, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(5.dp))

        // Subtotal, Delivery, and Total
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Service", fontSize = 16.sp, color = Color.Gray)
            Text(text = numberFormat.format(1.00), fontSize = 16.sp, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = numberFormat.format(28.99),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0XFF4B5E58)
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
                containerColor = Color(0xFFF4CE14)
            ),
            shape = RoundedCornerShape(25)
        ) {
            Text(
                text = "Checkout",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

    }
}