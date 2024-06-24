package com.example.asm.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.asm.R
import com.example.asm.ui.theme.Home
import com.example.asm.ui.theme.Make8

class Welcome {
    val galesioSemiBoldFont = FontFamily(
        Font(R.font.galesio_semi_bold, FontWeight.SemiBold)
    )

    @Composable
    fun Container(goToScreen: (String) -> Unit){
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.background),
                    contentScale = ContentScale.FillBounds
                )) {
            Main(goToScreen)
        }
    }

    @Composable
    fun Main(goToScreen: (String) -> Unit){
        val context = LocalContext.current

        Column {
            Text(
                text = "Make your".uppercase(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = galesioSemiBoldFont,
                color = Make8,
                modifier = Modifier.padding(start = 30.dp),
            )
            Text(
                text = "Home Beautiful".uppercase(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = galesioSemiBoldFont,
                color = Home,
                modifier = Modifier.padding(start = 30.dp, top = 15.dp),
            )
            Text(
                textAlign = TextAlign.Justify,
                text = "The best simple place where you discover most wonderful furnitures and make your home beautiful",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = galesioSemiBoldFont,
                color = com.example.asm.ui.theme.Body,
                modifier = Modifier.padding(horizontal = 60.dp, vertical = 35.dp),
            )
            TextButton(
                modifier = Modifier
                    .width(160.dp)
                    .height(55.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(
                        Home,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = 30.dp),
                onClick = {
                    goToScreen("login")
                }) {
                Text(text = "Get Started", color = com.example.asm.ui.theme.Button)
            }
        }
    }


}