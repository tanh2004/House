package com.example.asm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.asm.ui.theme.ASMTheme
import com.example.asm.ui.theme.TextColor4
import com.example.asm.ui.theme.TextColor5

class Success : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ASMTheme {
                Body()
            }
        }
    }
    @Composable
    fun Body(){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(color = Color.White),
        ) {
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = "SUCCESS!",fontSize = 40.sp)
                Image(painter = painterResource(id = R.drawable.success), contentDescription = null,
                    modifier = Modifier.size(200.dp))
                Image(painter = painterResource(id = R.drawable.stick), contentDescription = null,
                    modifier = Modifier.size(50.dp))
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Your order will be delivered soon.\n" +
                        "Thank you for choosing our app!", color = Color.Gray)
                Spacer(modifier = Modifier.height(20.dp))
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(
                            TextColor4,
                            shape = MaterialTheme.shapes.small
                        ),
                    onClick = {

                    }) {
                    Text(
                        text = "Track your orders",
                        color = TextColor5,
                        fontSize = 18.sp,
//                    fontFamily = FontFamily(Font(R.font.nunitosans)),
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .align(Alignment.CenterHorizontally)
                        .border(1.dp, TextColor4, shape = MaterialTheme.shapes.small)
                        .background(
                            TextColor5,
                            shape = MaterialTheme.shapes.small
                        ),
                    onClick = {

                    }) {
                    Text(
                        text = "BACK TO HOME",
                        color = TextColor4,
                        fontSize = 18.sp,
//                    fontFamily = FontFamily(Font(R.font.nunitosans)),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreView() {
        ASMTheme {
            Body()
        }
    }
}