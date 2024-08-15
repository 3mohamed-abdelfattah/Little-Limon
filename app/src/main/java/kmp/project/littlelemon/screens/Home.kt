package kmp.project.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kmp.project.littlelemon.R
import kmp.project.littlelemon.navigation.Profile

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        TopBar(navController)
        Spacer(modifier = Modifier.height(1.dp))
        RestaurantInfo()
        Spacer(modifier = Modifier.height(16.dp))
        CategoryTabs()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = (painterResource(id = R.drawable.logo)),
                contentDescription = "App Logo",
                modifier = Modifier
                    .width(200.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.FillWidth
            )
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(shape = CircleShape)
                    .clickable { navController.navigate(Profile.route) }
                    .align(
                        Alignment.TopEnd
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = " Profile"
                )
            }
        }
    }
}

@Composable
fun RestaurantInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF495E57))
            .padding(10.dp)
    ) {
        Text(
            text = "Little Lemon",
            style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.SemiBold,
            color = Color(0xFFF4CE14)
        )
        Text(text = "Egypt", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.width(width = 220.dp)) {
                Text(
                    text = "We are a family owned Mediterranean restaurant,focused on traditional recipes served with a modern twist.",
                    style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Normal,
                    color = Color.White, textAlign = TextAlign.Left,
                )
            }
            Image(
                painter = painterResource(id = R.drawable.main_img),
                contentDescription = "Main Image",
                Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .size(150.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Enter search phrase", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black, shape = RoundedCornerShape(15.dp)),
            shape = RoundedCornerShape(15.dp)
        )
    }
}

@Composable
fun CategoryTabs() {
    val categories = listOf("Starters", "Mains", "Desserts", "Drinks")
    Column {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(
                text = "ORDER BY DELIVERY!",
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
            Icon(
                painter = painterResource(id = R.drawable.van_logo),
                contentDescription = "van logo",
                modifier = Modifier.size(40.dp), Color(0XFF495E57)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { category ->
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(Color(0xFFedefee))) {
                    Text(
                        category,
                        color = Color(0xFF546861),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

