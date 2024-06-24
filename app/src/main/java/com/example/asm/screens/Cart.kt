package com.example.asm.screens

import android.widget.Toast
import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.asm.R
import com.example.asm.models.Cart
import com.example.asm.ui.theme.TextColor10
import com.example.asm.ui.theme.TextColor11
import com.example.asm.ui.theme.TextColor13
import com.example.asm.ui.theme.TextColor4
import com.example.asm.ui.theme.TextColor5
import com.example.asm.ui.theme.TextColor7
import com.example.asm.ui.theme.TextColor8
import com.example.asm.ui.theme.TextColor9

class Cart {
    @Composable
    fun Container(
        goToScreen: (String) -> Unit,
        updateCart: (Cart) -> Unit,
        cartInfo: List<Cart>,
        clearCart: () -> Unit
    ) {
        Body(
            goToScreen = goToScreen,
            updateCart = { updateCart(it) },
            cartInfo = cartInfo,
            clearCart = { clearCart() }
        )
    }


    private fun calculateTotal(cartInfo: List<Cart>): Int {
        return cartInfo.sumOf { it.product.price * it.quantity }
    }

    @Composable
    fun Body(
        goToScreen: (String) -> Unit,
        updateCart: (Cart) -> Unit,
        cartInfo: List<Cart>,
        clearCart: () -> Unit
    ) {var value = remember { mutableStateOf("") }

        val total = calculateTotal(cartInfo)
        val context = LocalContext.current


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "icon back",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { goToScreen("home") }
                )
                Text(
                    text = "My cart",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Surface(modifier = Modifier.size(20.dp)) {

                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(0.dp, 2.dp)
            ) {
                LazyColumn {
                    items(cartInfo.size) { index ->
                        RenderItem(cart = cartInfo[index], updateCart)
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = value.value,
                    onValueChange = { value.value = it },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .shadow(20.dp, spotColor = Color.Gray, ambientColor = TextColor10)
                        .background(
                            Color.Transparent,
                            shape = RoundedCornerShape(0.dp, 0.dp, 15.dp)
                        )
                        .clip(shape = RoundedCornerShape(15.dp, 0.dp, 0.dp, 15.dp)),
                    placeholder = {
                        Text(
                            text = "Enter your promo code",
                            color = TextColor11,
                            fontSize = 16.sp
                        )
                    },

                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,

                        ),
                )
                Image(
                    painter = painterResource(id = R.drawable.enter),
                    contentDescription = "icon next",
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp)
                        .clip(shape = RoundedCornerShape(0.dp, 15.dp, 15.dp, 0.dp))
                        .shadow(20.dp, spotColor = TextColor9, ambientColor = TextColor9)
                        .background(
                            color = TextColor10,
                            shape = RoundedCornerShape(0.dp, 15.dp, 15.dp, 0.dp)
                        )
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(67.dp)
                    .padding(0.dp, 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total: ",
                    color = TextColor11,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "$ $total",
                    color = TextColor13,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )


            }
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
                    clearCart()
                    Toast.makeText(context, "Mua hàng thành công", Toast.LENGTH_LONG).show()
                    goToScreen("success")
                }) {
                Text(
                    text = "Check out",
                    color = TextColor5,
                    fontSize = 18.sp,
//                    fontFamily = FontFamily(Font(R.font.nunitosans)),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    @Composable
    fun RenderItem(cart: Cart, updateCart: (Cart) -> Unit) {
        val product = cart.product

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.images[0])
                .size(Size.ORIGINAL)
                .build()
        )

        Column(
            modifier = Modifier
                .border(
                    width = 0.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(15.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Image(
                    painter = painter, contentDescription = "image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(color = Color.LightGray, shape = RoundedCornerShape(15.dp))
                )

                Column(modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .padding(20.dp, 0.dp, 0.dp, 0.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(24.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = product.name,
                                fontSize = 14.sp,
                                color = TextColor7
                            )
                            Image(
                                painter = painterResource(id = R.drawable.delete),
                                contentDescription = "icon delete",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable { /* Handle delete item from cart */ }
                            )
                        }
                        Text(
                            text = "$ ${product.price}",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.pluss),
                            contentDescription = "icon minus",
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                                .clip(shape = RoundedCornerShape(5.dp))
                                .background(
                                    color = TextColor8,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .clickable {
                                    updateCart(cart.copy(quantity = -1))  // Giảm số lượng sản phẩm
                                }
                        )

                        Text(
                            text = cart.quantity.toString(),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(15.dp, 0.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.minus),
                            contentDescription = "icon plus",
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                                .clip(shape = RoundedCornerShape(5.dp))
                                .background(
                                    color = TextColor8,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .clickable {
                                    updateCart(cart.copy(quantity = 1))  // Tăng số lượng sản phẩm
                                }
                        )

                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = TextColor9)
            )
        }
    }

//    @Preview(showBackground = true)
//    @Composable
//    fun GreetingPreview() {
//        AssignmentTheme {
//            Container()
//        }
//    }
}