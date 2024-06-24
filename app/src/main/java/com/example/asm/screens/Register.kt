package com.example.asm.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.asm.R
import com.example.asm.helpers.RetrofitAPI
import com.example.asm.httpmodels.RegisterRequestModel
import com.example.asm.httpmodels.RegisterResponseModel
import com.example.asm.ui.theme.Button
import com.example.asm.ui.theme.Home

class Register {
    val merriweather = FontFamily(
        Font(R.font.merriweather)
    )

    @Composable
    fun Container(goToScreen: (String) -> Unit){
        Body(goToScreen)
    }

    @Composable
    fun Body(goToScreen: (String) -> Unit){
        val context = LocalContext.current;
        var password by rememberSaveable {
            mutableStateOf("123")
        }
        var email by rememberSaveable {
            mutableStateOf("nguyenthanhtanh14062004@gmail.com")
        }
        var confirmPassword by rememberSaveable {
            mutableStateOf("")
        }
        var name by rememberSaveable {
            mutableStateOf("tanh")
        }

        fun registerCallback(response: RegisterResponseModel?){
            Toast.makeText(context, "Success to register", Toast.LENGTH_LONG).show()
            goToScreen("login")
        }

        fun onRegisterClick(){
            try {
                val body = RegisterRequestModel(name, email, password)
                //Call api
                val api = RetrofitAPI()
                api.register(body, ::registerCallback)
            }catch (e: Exception){
                Toast.makeText(context, "Failed to register", Toast.LENGTH_LONG).show()
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 60.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Divider(
                        color = com.example.asm.ui.theme.Body,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(26.dp)
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .size(60.dp)
                    )
                    Divider(
                        color = com.example.asm.ui.theme.Body,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(26.dp)
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                }
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Welcome".uppercase(),
                    fontSize = 30.sp,
                    fontFamily = merriweather,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(35.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(530.dp)
                        .background(Button)
                        .shadow(elevation = 5.dp),
                ) {
                    Spacer(modifier = Modifier.height(35.dp))
                    TextField(
                        value = name,
                        onValueChange = {name = it
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = com.example.asm.ui.theme.Body,
                            unfocusedContainerColor = Button,
                            focusedContainerColor = Button,
                            focusedLabelColor = com.example.asm.ui.theme.Body,
                        ),
                        label = {Text("Name")},
//                    placeholder = {Text("Enter your email")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 13.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email),

                        )
                    Spacer(modifier = Modifier.height(26.dp))
                    TextField(
                        value = email,
                        onValueChange = {email = it
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = com.example.asm.ui.theme.Body,
                            unfocusedContainerColor = Button,
                            focusedContainerColor = Button,
                            focusedLabelColor = com.example.asm.ui.theme.Body,
                        ),
                        label = {Text("Email")},
//                    placeholder = {Text("Enter your email")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 13.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email),
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    TextField(
                        value = password,
                        onValueChange = {password = it
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = com.example.asm.ui.theme.Body,
                            unfocusedContainerColor = Button,
                            focusedContainerColor = Button,
                            focusedLabelColor = com.example.asm.ui.theme.Body
                        ),
                        label = {Text("Password")},
//                    placeholder = {Text("Enter your password")},

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 13.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        trailingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.eye),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }

                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    TextField(
                        value = confirmPassword,
                        onValueChange = {confirmPassword = it
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = com.example.asm.ui.theme.Body,
                            unfocusedContainerColor = Button,
                            focusedContainerColor = Button,
                            focusedLabelColor = com.example.asm.ui.theme.Body
                        ),
                        label = {Text("Confirm Password")},
//                    placeholder = {Text("Enter your password")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 13.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        trailingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.eye),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }

                    )


                    Spacer(modifier = Modifier.height(46.dp))
                    TextButton(
                        modifier = Modifier
                            .width(307.dp)
                            .height(55.dp)
                            .align(Alignment.CenterHorizontally)
                            .background(
                                Home,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(horizontal = 40.dp),
                        onClick = {
                            onRegisterClick()
                        }) {
                        Text(text = "SIGN UP", color = Button, fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.height(26.dp))
                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Already have an account?", color = com.example.asm.ui.theme.Body,
                            modifier = Modifier.align(Alignment.CenterVertically))
                        TextButton(onClick = { goToScreen("login") }) {
                            Text(text = "SIGN IN", color = Home, fontWeight = FontWeight.Bold)
                        }
                    }
                }

            }

        }

    }
}