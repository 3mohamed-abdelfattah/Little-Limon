package kmp.project.littlelemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding() {
    Column(modifier = Modifier.fillMaxSize()) {

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

            var firstName by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }

            Text(text = "First Name", fontWeight = FontWeight.W500)

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Last Name", fontWeight = FontWeight.W500)

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Email", fontWeight = FontWeight.W500)

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
            )

        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(
                    0xFFF4CE14
                )
            ),
            shape = RoundedCornerShape(25),
            border = BorderStroke(2.dp, Color(0x2F333333))
        ) {
            Text(text = "Register", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro")
@Composable
fun OnboardingPreview() {
    Onboarding()
}