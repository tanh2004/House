package com.example.asm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.asm.models.UserInfo
import com.example.asm.screens.Cart
import com.example.asm.screens.Detail
import com.example.asm.screens.Home
import com.example.asm.screens.Login
import com.example.asm.screens.Logout
import com.example.asm.screens.Register
import com.example.asm.screens.Success
import com.example.asm.screens.Welcome
import com.example.asm.ui.theme.ASMTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ASMTheme {
                Body()
            }
        }
    }

    @Composable
    fun Body() {
        val loginScreen = Login()
        val homeScreen = Home()
        val detailScreen = Detail()
        val registerScreen = Register()
        val welcomeScreen = Welcome()
        val cartScreen = Cart()
        val successScreen = Success()
        val logoutScreen = Logout()

        //Khai báo navigation quản lý chuyển đổi các màn hình
        val navController = rememberNavController()

        //Chuyển màn hình
        fun goToScreen(screen: String) {
            navController.navigate(screen)
        }

        //Đọc từ shared preference
        fun readShared(): UserInfo {
            val sharedPref = getSharedPreferences("user", MODE_PRIVATE)
            return UserInfo(
                null,
                email = sharedPref.getString("email", null),
                null,
                null
            )
        }
        //Khai báo state chứa info
        var userInfo by remember { mutableStateOf(readShared()) }

        //Lưu vào shared preference
        fun saveShared(user: UserInfo) {
            val sharedPref = getSharedPreferences("user", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("email", user.email)
                apply()
            }
            userInfo = user
        }
        //thông tin giỏ hàng
        var cartInfo by remember { mutableStateOf(listOf<com.example.asm.models.Cart>()) }

        // cập nhật giỏ hàng
        val context = LocalContext.current
        fun updateCart(item: com.example.asm.models.Cart) {
            // kiểm tra item có trong giỏ hàng chưa
            val index = cartInfo.indexOfFirst { it.product._id == item.product._id }
            if (index == -1) {
                // chưa có thì thêm vào
                cartInfo = cartInfo + item

                Toast.makeText(context, "Thêm sản phẩm", Toast.LENGTH_LONG).show()
            } else {
                // có rồi thì cập nhật số lượng
                // nếu số lượng giảm về 0 thì xóa item đó
                if (item.quantity + cartInfo[index].quantity == 0) {
                    cartInfo = cartInfo.filterIndexed { i, _ -> i != index }
                } else {
                    cartInfo = cartInfo.mapIndexed { i, cart ->
                        if (i == index) {
                            com.example.asm.models.Cart(
                                product = cart.product,
                                quantity = cart.quantity + item.quantity
                            )
                        } else {
                            cart
                        }
                    }
                }
            }
        }
        fun clearCart(){
            cartInfo = listOf()
        }
        NavHost(
            navController = navController,
            startDestination = if (userInfo.email.isNullOrEmpty()) "welcome" else "home"
        ) {
            composable("login") {
                loginScreen.Container(
                    goToScreen = { goToScreen(it) },
                    saveUserInfo = { saveShared(it) }
                )
            }
            composable("home") {
                homeScreen.Container(
                    saveUserInfo = { saveShared(it) },
                    goToScreen = { goToScreen(it) }
                )
            }
            composable(
                "detail/{value}",
                arguments = listOf(navArgument("value") { defaultValue = "" })
            ) { backStackEntry ->
                detailScreen.Container(backStackEntry.arguments?.getString("value"),
                    goToScreen = { goToScreen(it) },
                    updateCart = { updateCart(it) })
            }
            composable("register") {
                registerScreen.Container(
                    goToScreen = { goToScreen(it) }
                )
            }
            composable("welcome") {
                welcomeScreen.Container(
                    goToScreen = { goToScreen(it) }
                )
            }
            composable("cart") {
                cartScreen.Container(
                    goToScreen = { goToScreen(it) }, updateCart = { updateCart(it) },
                    cartInfo = cartInfo, clearCart = { clearCart()}
                )
            }
            composable("success") {
                successScreen.Container(
                    goToScreen = { goToScreen(it) }
                )
            }
            composable("logout") {
                logoutScreen.Container(
                    saveUserInfo = { saveShared(it) }
                )
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




