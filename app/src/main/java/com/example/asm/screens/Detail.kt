package com.example.asm.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.asm.R
import com.example.asm.helpers.RetrofitAPI
import com.example.asm.httpmodels.ProductDeatilResponseModel
import com.example.asm.httpmodels.ProductModel
import com.example.asm.httpmodels.ProductModell
import com.example.asm.models.Cart
import com.example.asm.ui.theme.TextColor2
import com.example.asm.ui.theme.TextColor3
import com.example.asm.ui.theme.TextColor4
import com.example.asm.ui.theme.TextColor5
import com.example.asm.ui.theme.TextColor8
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


class Detail {

    @Composable
    fun Container(value: String?,
                  goToScreen: (String) -> Unit,
                  updateCart: (com.example.asm.models.Cart) -> Unit){
        Body(value, goToScreen = goToScreen, updateCart)
    }
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Body(value: (String)?, goToScreen: (String) -> Unit, updateCart: (com.example.asm.models.Cart) -> Unit) {

        val context = LocalContext.current

        var product by remember { mutableStateOf<ProductModell?>(null) }
        fun productDetailCallback(response: ProductDeatilResponseModel?) {
            if (response != null) {
                product = response.data
            }
        }

        fun getProductDetail() {
            try {
                val api = RetrofitAPI()
                val id = value ?: ""
                api.getProductById(id, ::productDetailCallback)
            } catch (e: Exception) {
                Toast.makeText(context, "Failed to get product", Toast.LENGTH_LONG).show()
            }
        }
        getProductDetail()


        if (product != null) {
            val pagerState = rememberPagerState()

            //This column wraps the whole screen
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                //The top half of the sreen
                //Using box to not let the back icon and three circles in order (column or row)
                //Instead we can adjust these items wherever we want
                Box {
                    HorizontalPager(
                        count = product!!.images.size,
                        state = pagerState
                    ) { page ->
//                    check.value = page
                        //Align the image to the right
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.End
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(width = 360.dp, height = 465.dp)
                                    //Place clip property in front of paint property
                                    .clip(
                                        shape = RoundedCornerShape(
                                            topStart = 0.dp,
                                            topEnd = 0.dp,
                                            bottomStart = 50.dp,
                                            bottomEnd = 0.dp
                                        )
                                    )
                            ) {
                                //Load image online
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(product!!.images[page])
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }

                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier.align(Alignment.BottomEnd)
                            .padding(40.dp, 15.dp),
//                    indicatorWidth = if (check.value == pagerState.currentPage) 20.dp else 10.dp,
                        indicatorWidth = 15.dp,
                        indicatorHeight = 5.dp,
                        indicatorShape = RoundedCornerShape(2.dp),
                        activeColor = TextColor4,
                        inactiveColor = TextColor5
                    )


                    Surface(
                        modifier = Modifier
                            .padding(start = 52.dp, top = 50.dp)
                            .shadow(
                                elevation = 15.dp,
                                spotColor = Color.Gray,
                                ambientColor = Color.Gray
                            )
                            .clip(
                                shape = RoundedCornerShape(
                                    topStart = 5.dp,
                                    topEnd = 5.dp,
                                    bottomStart = 5.dp,
                                    bottomEnd = 5.dp
                                )
                            )

                    ) {
                        Image(
                            modifier = Modifier
                                .background(TextColor5)
                                .padding(10.dp)
                                .size(25.dp)
                                .clickable { goToScreen("home") },
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "",
                        )
                    }

                    //Three circles part
                    Column(
                        modifier = Modifier
                            .padding(start = 52.dp, top = 150.dp)
                            //Place shadow first then clip after
                            .shadow(
                                elevation = 15.dp,
                                spotColor = Color.Gray,
                                ambientColor = Color.Gray
                            )
                            .clip(shape = RoundedCornerShape(35.dp))
                    ) {
                        Image(
                            modifier = Modifier
                                .background(TextColor5)
                                .padding(26.dp)
                                .scale(3f),
                            painter = painterResource(id = R.drawable.a),
                            contentDescription = "",
                        )
                        Image(
                            modifier = Modifier
                                .background(TextColor5)
                                .padding(26.dp)
                                .scale(3f),
                            painter = painterResource(id = R.drawable.b),contentDescription = "",
                        )
                        Image(
                            modifier = Modifier
                                .background(TextColor5)
                                .padding(26.dp)
                                .scale(3f),
                            painter = painterResource(id = R.drawable.c),
                            contentDescription = "",
                        )
                    }

                }

                //The bottom half of the screen
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = product!!.name,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
//                        fontFamily = FontFamily(Font(R.font.galesio_semi_bold)),
                        color = TextColor2
                    )
                    Spacer(modifier = Modifier.height(5.dp))


                    //The price and quantity
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$ ${product!!.price}",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
//                        fontFamily = FontFamily(Font(R.font.nunitosans)),
                            color = TextColor2
                        )
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(5.dp))
                                    .background(TextColor8)
                                    .padding(horizontal = 12.dp, vertical = 2.dp),
                                text = "+",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
//                            fontFamily = FontFamily(Font(R.font.nunitosans)),
                                color = TextColor2
                            )
                            Spacer(modifier = Modifier.width(15.dp))

                            Text(
                                text = product!!.quantity.toString(),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,fontSize = 20.sp,
//                            fontFamily = FontFamily(Font(R.font.nunitosans)),
                                color = TextColor2
                            )
                            Spacer(modifier = Modifier.width(15.dp))

                            Text(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(5.dp))
                                    .background(TextColor8)
                                    .padding(horizontal = 12.dp, vertical = 2.dp),
                                text = "-",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
//                            fontFamily = FontFamily(Font(R.font.nunitosans)),
                                color = TextColor2
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    //The rating and comment
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.scale(2f),
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "",
                        )

                        Spacer(modifier = Modifier.width(15.dp))

                        Text(
                            text = "4.5",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
//                            fontFamily = FontFamily(Font(R.font.nunitosans)),
                            color = TextColor2
                        )

                        Spacer(modifier = Modifier.width(35.dp))

                        Text(
                            text = "(50 reviews)",
                            fontSize = 18.sp,
//                            fontFamily = FontFamily(Font(R.font.nunitosans)),
                            color = TextColor3
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Minimal Stand is made of by natural wood. The design that is very simple and minimal. This is truly one of the best furnitures in any family for now. With 3 different colors, you can easily select the best match for your home. ",
//                    fontFamily = FontFamily(Font(R.font.nunitosans)),
                        fontSize = 16.sp,
                        color = TextColor3
                    )
                    Spacer(modifier = Modifier.height(70.dp))
                    //Button part
                    Row(
                        modifier = Modifier.padding(top = 15.dp)
                    ) {Button(
                        modifier = Modifier
                            .height(60.dp)
                            .shadow(
                                elevation = 5.dp,
                                spotColor = Color.LightGray,
                                ambientColor = Color.LightGray
                            ),
                        onClick = { /*TODO*/ },
//                        border = BorderStroke(10.dp, Color.Transparent),
                        shape = RoundedCornerShape(7.dp),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = TextColor8)
                    ) {
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.marker),
                            contentDescription = "",
                        )
                    }

                        Spacer(modifier = Modifier.width(15.dp))

                        Button(
                            modifier = Modifier
                                .height(60.dp)
                                .weight(1f)
                                .shadow(
                                    elevation = 15.dp,
                                    spotColor = Color.Gray,
                                    ambientColor = Color.Gray
                                ),
                            onClick = { /*TODO*/
                                val item = Cart(product!!, 1)
                                updateCart(item)
                            },
//                        border = BorderStroke(10.dp, Color.Transparent),
                            shape = RoundedCornerShape(7.dp),
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = TextColor4)
                        ) {
                            Text(text = "Add to cart", color = TextColor5, fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }
}
