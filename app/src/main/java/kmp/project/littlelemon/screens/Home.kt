package kmp.project.littlelemon.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
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
        HorizontalDivider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        MenuScreen(menuItems = sampleMenuItems)
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
                    .size(50.dp)
                    .clip(shape = CircleShape)
                    .clickable { navController.navigate(Profile.route) }
                    .align(
                        Alignment.TopEnd
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = " Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .border(
                            BorderStroke(2.dp, Color(0XFFEDEFEE)), CircleShape
                        )

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
            placeholder = { Text("Enter search phrase", color = Color(0XFF767171)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0XFF546861)
            )
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
                .horizontalScroll(rememberScrollState())
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
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

@Composable
fun MenuScreen(menuItems: List<MenuItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        items(menuItems) { menuItem ->
            MenuItemCard(menuItem)
            HorizontalDivider(
                color = Color(0XFFE7E7E7),
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun MenuItemCard(menuItem: MenuItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = menuItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Box(modifier = Modifier.width(260.dp)) {
                    Text(
                        text = menuItem.description,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis, color = Color.DarkGray, fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "\$${menuItem.price}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF546861)
                )
            }
            Image(
                painter = rememberAsyncImagePainter(menuItem.image),
                contentDescription = menuItem.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MenuScreen(menuItems = sampleMenuItems)
}


data class MenuItem(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

val sampleMenuItems = listOf(
    MenuItem(
        id = 1,
        title = "Greek Salad",
        description = "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
        price = "10",
        image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
        category = "starters"
    ),
    MenuItem(
        id = 2,
        title = "Lemon Desert",
        description = "Traditional homemade Italian Lemon Ricotta Cake.",
        price = "10",
        image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/lemonDessert%202.jpg?raw=true",
        category = "desserts"
    ),
    MenuItem(
        id = 3,
        title = "Grilled Fish",
        description = "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil.",
        price = "10",
        image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/grilledFish.jpg?raw=true",
        category = "mains"
    ),
    MenuItem(
        id = 4,
        title = "Pasta",
        description = "Penne with fried aubergines, cherry tomatoes, tomato sauce, fresh chili, garlic, basil & salted ricotta cheese.",
        price = "10",
        image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
        category = "mains"
    ),
    MenuItem(
        id = 5,
        title = "Bruschetta",
        description = "Oven-baked bruschetta stuffed with tomatoes and herbs.",
        price = "10",
        image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/bruschetta.jpg?raw=true",
        category = "starters"
    )
)
