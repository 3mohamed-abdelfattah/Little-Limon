package kmp.project.littlelemon.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import kmp.project.littlelemon.R
import kmp.project.littlelemon.navigation.Profile

val MerriWeather = FontFamily(Font(R.font.merriweather_bold))
val Barlow = FontFamily(Font(R.font.barlow_regular))

val customLogoTextStyle = TextStyle(
    fontFamily = MerriWeather,
    fontSize = 42.sp
)
val customTitleTextStyle = TextStyle(
    fontFamily = Barlow,
    fontSize = 32.sp
)
val customTextTextStyle = TextStyle(
    fontFamily = Barlow,
    fontSize = 20.sp
)

@Composable
fun HomeScreen(navController: NavController) {

    val keyboardController = LocalSoftwareKeyboardController.current

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        var searchPhrase by remember { mutableStateOf("") }
        var selectedCategory by remember { mutableStateOf<String?>(null) }

        val filteredMenuItems = sampleMenuItems.filter { menuItem ->
            (searchPhrase.isBlank() || menuItem.title.contains(
                searchPhrase, ignoreCase = true
            ) || menuItem.description.contains(
                searchPhrase, ignoreCase = true
            )) && (selectedCategory == null || menuItem.category.equals(
                selectedCategory, ignoreCase = true
            ))
        }

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
            Spacer(modifier = Modifier.height(1.dp))
            RestaurantInfo(searchPhrase, onSearchPhraseChange = { searchPhrase = it })
            Spacer(modifier = Modifier.height(16.dp))
            CategoryTabs(selectedCategory = selectedCategory, onCategorySelected = { category ->
                selectedCategory = if (selectedCategory == category) null else category
            })
            HorizontalDivider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            MenuScreen(menuItems = filteredMenuItems)
        }
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
            Box(modifier = Modifier
                .size(40.dp)
                .clip(shape = CircleShape)
                .clickable { navController.navigate(Profile.route) }
                .align(
                    Alignment.TopEnd
                )) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = " Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.border(
                        BorderStroke(2.dp, Color(0XFFEDEFEE)), CircleShape
                    )

                )
            }
        }
    }
}

@Composable
fun RestaurantInfo(searchPhrase: String, onSearchPhraseChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF495E57))
            .padding(10.dp)
    ) {
        Text(
            text = "Little Lemon",
            style = customLogoTextStyle,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFFF4CE14),
        )
        Text(text = "Egypt", style = customTitleTextStyle, color = Color.White)
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.weight(1f)) {
                Text(
                    text = "We are a family owned Mediterranean restaurant,focused on traditional recipes served with a modern twist.",
                    style = customTextTextStyle, fontWeight = FontWeight.Normal,
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
            value = searchPhrase,
            onValueChange = onSearchPhraseChange,
            placeholder = { Text("Enter search phrase", color = Color(0XFF767171)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
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
fun CategoryTabs(selectedCategory: String?, onCategorySelected: (String?) -> Unit) {
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
                modifier = Modifier.size(40.dp),
                Color(0XFF495E57)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(10.dp), horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            categories.forEach { category ->
                Button(
                    onClick = { onCategorySelected(category) },
                    colors = ButtonDefaults.buttonColors(
                        if (selectedCategory == category) Color(0xFF495E57) else Color(0xFFedefee)
                    )
                ) {
                    Text(
                        category,
                        color = if (selectedCategory == category) Color.White else Color(0xFF546861),
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
                        overflow = TextOverflow.Ellipsis,
                        color = Color.DarkGray,
                        fontSize = 15.sp
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
        image = "https://sugar-n-spicegals.com/wp-content/uploads/2017/01/IMG_2466.jpg",
        category = "desserts"
    ),
    MenuItem(
        id = 3,
        title = "Grilled Fish",
        description = "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil.",
        price = "10",
        image = "https://www.licious.in/blog/wp-content/uploads/2020/12/Grilled-Fish.jpg",
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
    ),
    MenuItem(
        id = 6,
        title = "Caesar Salad",
        description = "Crispy romaine lettuce with Caesar dressing, croutons, and parmesan cheese.",
        price = "12",
        image = "https://www.seriouseats.com/thmb/Fi_FEyVa3_-_uzfXh6OdLrzal2M=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/the-best-caesar-salad-recipe-06-40e70f549ba2489db09355abd62f79a9.jpg",
        category = "starters"
    ),
    MenuItem(
        id = 7,
        title = "Tiramisu",
        description = "Classic Italian dessert made with mascarpone cheese, espresso, and cocoa.",
        price = "15",
        image = "https://lustensile.fr/wp-content/uploads/2023/04/tiramisu-sans-oeufs-jpg.webp",
        category = "desserts"
    ),
    MenuItem(
        id = 8,
        title = "Margherita Pizza",
        description = "Classic pizza with fresh tomatoes, mozzarella, and basil.",
        price = "20",
        image = "https://fr.ooni.com/cdn/shop/articles/Margherita-9920.jpg?crop=center&height=800&v=1644590066&width=800",
        category = "mains"
    ),
    MenuItem(
        id = 9,
        title = "Chocolate Cake",
        description = "Rich and moist chocolate cake with a velvety ganache.",
        price = "18",
        image = "https://sugargeekshow.com/wp-content/uploads/2023/10/easy_chocolate_cake_slice-500x375.jpg",
        category = "desserts"
    ),
    MenuItem(
        id = 10,
        title = "Mushroom Soup",
        description = "Creamy soup made with wild mushrooms and fresh herbs.",
        price = "8",
        image = "https://rainbowplantlife.com/wp-content/uploads/2022/11/Mushroom-soup-cover-image-1-of-1.jpg",
        category = "starters"
    ),
    MenuItem(
        id = 11,
        title = "Garlic Bread",
        description = "Freshly baked bread topped with garlic butter and herbs.",
        price = "6",
        image = "https://www.allrecipes.com/thmb/ymrjQ3GFq_Fc7Fu2yfvIj108tcM=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/21080-great-garlic-bread-DDMFS-4x3-e1c7b5c79fde4d629a9b7bce6c0702ed.jpg",
        category = "starters"
    ),
    MenuItem(
        id = 12,
        title = "Chicken Alfredo",
        description = "Fettuccine pasta with creamy Alfredo sauce and grilled chicken.",
        price = "22",
        image = "https://bellyfull.net/wp-content/uploads/2021/02/Chicken-Alfredo-blog-3.jpg",
        category = "mains"
    ),
    MenuItem(
        id = 13,
        title = "Beef Burger",
        description = "Juicy beef patty with cheddar cheese, lettuce, and tomato.",
        price = "15",
        image = "https://www.unileverfoodsolutions.co.za/dam/global-ufs/mcos/SOUTH-AFRICA/calcmenu/recipes/ZA-recipes/general/beef-burger/main-header.jpg",
        category = "mains"
    ),
    MenuItem(
        id = 14,
        title = "Spaghetti Carbonara",
        description = "Classic pasta dish with pancetta, egg yolk, and parmesan.",
        price = "18",
        image = "https://www.twopeasandtheirpod.com/wp-content/uploads/2023/01/Spaghetti-Carbonara168766.jpg",
        category = "mains"
    ),
    MenuItem(
        id = 15,
        title = "Fruit Tart",
        description = "Crispy tart filled with custard and topped with fresh fruit.",
        price = "12",
        image = "https://hips.hearstapps.com/hmg-prod/images/fruit-tart-index-65ef54d972bb1.jpg?crop=0.502xw:1.00xh;0.463xw,0&resize=1200:*",
        category = "desserts"
    ),
    MenuItem(
        id = 16,
        title = "Shrimp Cocktail",
        description = "Fresh shrimp served with tangy cocktail sauce.",
        price = "15",
        image = "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2004/4/21/0/bw2b09_shrimp_cocktail.jpg.rend.hgtvcom.616.462.suffix/1557928675507.webp",
        category = "starters"
    ),
    MenuItem(
        id = 17,
        title = "Apple Pie",
        description = "Homemade apple pie with a flaky crust and cinnamon.",
        price = "14",
        image = "https://mojo.generalmills.com/api/public/content/RF7rt2cyH0GGi0OySrmZnQ_webp_base.webp?v=ce55fe91&t=e724eca7b3c24a8aaa6e089ed9e611fd",
        category = "desserts"
    ),
    MenuItem(
        id = 18,
        title = "Lasagna",
        description = "Layers of pasta, meat sauce, and melted cheese.",
        price = "20",
        image = "https://thecozycook.com/wp-content/uploads/2022/04/Lasagna-Recipe-f.jpg",
        category = "mains"
    ),
    MenuItem(
        id = 19,
        title = "Minestrone Soup",
        description = "Hearty Italian soup with vegetables, beans, and pasta.",
        price = "10",
        image = "https://images.immediate.co.uk/production/volatile/sites/30/2021/03/Classic-Minestrone-Soup-13720e5.jpg?resize=768,574",
        category = "starters"
    ),
    MenuItem(
        id = 20,
        title = "Panna Cotta",
        description = "Creamy dessert topped with berry sauce.",
        price = "13",
        image = "https://static01.nyt.com/images/2023/08/10/multimedia/LH-Panna-Cotta-wczm/LH-Panna-Cotta-wczm-superJumbo.jpg",
        category = "desserts"
    ),
    MenuItem(
        id = 21,
        title = "Ribeye Steak",
        description = "Grilled ribeye steak with a side of roasted potatoes.",
        price = "30",
        image = "https://healthyrecipesblogs.com/wp-content/uploads/2022/09/ribeye-steak-featured.jpg",
        category = "mains"
    ),
    MenuItem(
        id = 22,
        title = "Caprese Salad",
        description = "Fresh tomatoes, mozzarella, and basil drizzled with balsamic.",
        price = "14",
        image = "https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2019/07/Caprese-Salad-main-1.jpg",
        category = "starters"
    ),
    MenuItem(
        id = 23,
        title = "Tuna Tartare",
        description = "Finely chopped tuna with avocado and soy sauce.",
        price = "18",
        image = "https://pinchandswirl.com/wp-content/uploads/2022/12/Tuna-Tartare-sq.jpg",
        category = "starters"
    ),
    MenuItem(
        id = 24,
        title = "Cheesecake",
        description = "Rich cheesecake with a graham cracker crust and fruit topping.",
        price = "15",
        image = "https://cakesbymk.com/wp-content/uploads/2023/11/Template-Size-for-Blog-Photos-24.jpg",
        category = "desserts"
    ),
    MenuItem(
        id = 25,
        title = "Roast Chicken",
        description = "Juicy roast chicken with herbs and garlic.",
        price = "25",
        image = "https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2022/10/Roast-Chicken-main.jpg",
        category = "mains"
    ),
    MenuItem(
        id = 26,
        title = "Garlic Shrimp",
        description = "Grilled shrimp with garlic butter sauce.",
        price = "22",
        image = "https://www.wholesomeyum.com/wp-content/uploads/2022/01/wholesomeyum-Lemon-Garlic-Butter-Shrimp-Recipe-19.jpg",
        category = "mains"
    ),
    MenuItem(
        id = 27,
        title = "Mango Sorbet",
        description = "Refreshing mango sorbet made with ripe mangoes.",
        price = "10",
        image = "https://www.acouplecooks.com/wp-content/uploads/2022/05/Mango-Sorbet-010.jpg",
        category = "desserts"
    ),
    MenuItem(
        id = 28,
        title = "Chicken Wings",
        description = "Crispy chicken wings with a spicy buffalo sauce.",
        price = "15",
        image = "https://images.immediate.co.uk/production/volatile/sites/30/2020/12/Air-Fryer-Chicken-Wings-d2c6fa4.jpg",
        category = "starters"
    ),
    MenuItem(
        id = 29,
        title = "Vegetable Stir-Fry",
        description = "Mixed vegetables stir-fried in a savory sauce.",
        price = "16",
        image = "https://www.wholesomeyum.com/wp-content/uploads/2020/11/wholesomeyum-Stir-Fry-Vegetables-15.jpg",
        category = "mains"
    ),
    MenuItem(
        id = 30,
        title = "Creme Brulee",
        description = "Classic French dessert with a crispy caramelized top.",
        price = "18",
        image = "https://www.dixiecrystals.com/sites/default/files/styles/recipe_image_node_full/public/recipe/classic-creme-brulee-dixie.jpg?itok=uwVz6bvH",
        category = "desserts"
    ),
    MenuItem(
        id = 31,
        title = "Salmon Fillet",
        description = "Grilled salmon fillet with a lemon butter sauce.",
        price = "28",
        image = "https://images.services.kitchenstories.io/iVj59av0wHJY3TWq3Ym32RuJvyg=/3840x0/filters:quality(85)/images.kitchenstories.io/wagtailOriginalImages/R3022-final-photo-3.jpg",
        category = "mains"
    ),
    MenuItem(
        id = 32,
        title = "Mozzarella Sticks",
        description = "Breaded mozzarella sticks served with marinara sauce.",
        price = "12",
        image = "https://recipes.net/wp-content/uploads/2023/05/sonic-mozzarella-sticks-recipe_a6ffe881d10a4fcfee765425c04d74b1.jpeg",
        category = "starters"
    ),
    MenuItem(
        id = 33,
        title = "Peach Cobbler",
        description = "Warm peach cobbler with a scoop of vanilla ice cream.",
        price = "14",
        image = "https://joyfoodsunshine.com/wp-content/uploads/2020/05/peach-cobbler-recipe-7.jpg",
        category = "desserts"
    ),
    MenuItem(
        id = 34,
        title = "Beef Stroganoff",
        description = "Tender beef strips in a creamy mushroom sauce over egg noodles.",
        price = "25",
        image = "https://www.seriouseats.com/thmb/Z16YKiURjKl0N6J_Mgy03eJHYX0=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__recipes__images__2014__12__20150110-beef-stroganoff-food-lab-28-c02205e4bcc64a1bb5c92c6e3262dc76.jpg",
        category = "mains"
    ),
    MenuItem(
        id = 35,
        title = "Bruschetta",
        description = "Grilled bread topped with fresh tomatoes, basil, and balsamic.",
        price = "12",
        image = "https://iod.keplrstatic.com/api/ar_1,c_crop,g_north/c_fill,dpr_auto,f_auto,q_70,w_750/mon_marche/REC20230616BruschettaTomatesbasilic.jpg",
        category = "starters"
    ),
    MenuItem(
        id = 36,
        title = "Fresh Orange Juice",
        description = "Freshly squeezed orange juice.",
        price = "8",
        image = "https://www.earthfoodandfire.com/wp-content/uploads/2018/04/Homemade-Orange-Juice.jpg",
        category = "drinks"
    ),
    MenuItem(
        id = 37,
        title = "Cappuccino",
        description = "Rich and creamy cappuccino with steamed milk foam.",
        price = "12",
        image = "https://recettes.vedrenne.fr/1269-large_default/cappuccino-pain-d-epices.jpg",
        category = "drinks"
    ),
    MenuItem(
        id = 38,
        title = "Iced Tea",
        description = "Chilled iced tea with a hint of lemon.",
        price = "7",
        image = "https://natashaskitchen.com/wp-content/uploads/2021/07/Iced-Tea-3-1-728x1092.jpg",
        category = "drinks"
    ),
    MenuItem(
        id = 39,
        title = "Mojito",
        description = "Refreshing mojito with mint, lime, and sparkling water.",
        price = "10",
        image = "https://kitchenswagger.com/wp-content/uploads/2020/07/mojito-recipe_0009_DSC_5509.jpg",
        category = "drinks"
    ),
    MenuItem(
        id = 40,
        title = "Espresso",
        description = "Strong and bold espresso shot.",
        price = "5",
        image = "https://cdn.rizopouloscoffee.gr/www/rizopoulos-coffee-espresso.jpg",
        category = "drinks"
    )
)