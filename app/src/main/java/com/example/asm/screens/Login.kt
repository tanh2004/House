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
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.asm.R
import com.example.asm.helpers.RetrofitAPI
import com.example.asm.httpmodels.LoginRequestModel
import com.example.asm.httpmodels.LoginResponseModel
import com.example.asm.models.UserInfo
import com.example.asm.ui.theme.Body
import com.example.asm.ui.theme.Home

class Login {
    val merriweather = FontFamily(
        Font(R.font.merriweather)
    )
    
    @Composable
    fun Container(goToScreen: (String) -> Unit,
                  saveUserInfo: (UserInfo) -> Unit){
        val context = LocalContext.current;
        var password by rememberSaveable {
            mutableStateOf("")
        }
        var email by rememberSaveable {
            mutableStateOf("")
        }
        var passwordVisible by remember {
            mutableStateOf(false)
        }
        var isLoading by remember {
            mutableStateOf(false)
        }

        fun onLoginCallback(response: LoginResponseModel?){
            if(response != null){
                isLoading = false
                saveUserInfo(
                    UserInfo(
                        id = response.data?.id,
                        name = response.data?.name,
                        email = response.data?.email,
                        password = null
                    )
                )
            }else{
                //login thất bại
                Toast.makeText(context, "Failed to login", Toast.LENGTH_LONG).show()
            }
        }

        fun onLoginClick(){
            try {
                isLoading = true
                val body: LoginRequestModel = LoginRequestModel(email, password)
                val api = RetrofitAPI()
                api.login(body, ::onLoginCallback)
            }catch (e: Exception){
                //login thất bại
                Toast.makeText(context, "Failed to login", Toast.LENGTH_LONG).show()
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .padding(top = 40.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Divider(
                        color = Body,
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
                        color = Body,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(26.dp)
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                }
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Hello!",
                    fontSize = 30.sp,
                    fontFamily = merriweather,
                    fontWeight = FontWeight.Bold,
                    color = Body,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Welcome back".uppercase(),
                    fontSize = 30.sp,
                    fontFamily = merriweather,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(46.dp))
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(430.dp)
                        .background(com.example.asm.ui.theme.Button)
                        .shadow(elevation = 5.dp),
                ){
                    Spacer(modifier = Modifier.height(36.dp))
                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Body,
                            unfocusedContainerColor = com.example.asm.ui.theme.Button,
                            focusedContainerColor = com.example.asm.ui.theme.Button,
                            focusedLabelColor = Body,
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
                        onValueChange = {
                            password = it
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Body,
                            unfocusedContainerColor = com.example.asm.ui.theme.Button,
                            focusedContainerColor = com.example.asm.ui.theme.Button,
                            focusedLabelColor = Body
                        ),
                        label = {Text("Password")},
//                    placeholder = {Text("Enter your password")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 13.dp),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                    Spacer(modifier = Modifier.height(36.dp))
                    Text(
                        text = "Forgot Password",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                    TextButton(
                        modifier = Modifier
                            .width(310.dp)
                            .height(55.dp)
                            .align(Alignment.CenterHorizontally)
                            .background(
                                Home,
                                shape = MaterialTheme.shapes.small
                            )
                        ,
                        onClick = {
                            onLoginClick()

                        }) {
                        Text(text = "Login", color = com.example.asm.ui.theme.Button, fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.height(26.dp))
                    TextButton(onClick = { goToScreen("register") },
                        modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(
                            text = "Sign Up".uppercase(),
                            fontSize = 20.sp
                        )
                    }

                }


            }

        }
    }

}