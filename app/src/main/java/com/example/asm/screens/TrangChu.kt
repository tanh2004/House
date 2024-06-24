package com.example.asm.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.asm.R
import com.example.asm.helpers.RetrofitAPI
import com.example.asm.httpmodels.ProductModel
import com.example.asm.httpmodels.ProductResponseModel
import com.example.asm.models.UserInfo


class TrangChu {
    @Composable
    fun Container(goToScreen: (String) -> Unit, saveUserInfo: (UserInfo) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(20.dp)
        ) {
            TopBar(goToScreen = {goToScreen(it)}, saveUserInfo = {saveUserInfo(it)})
            Categories()
            ProductGrid(goToScreen = {goToScreen(it)})
        }
    }

    @Composable
    fun TopBar(goToScreen: (String) -> Unit, saveUserInfo: (UserInfo) -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { saveUserInfo(UserInfo(null, null, null, null)) }) {
                Image(
                    painter = painterResource(id = R.drawable.exit),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
            Column(
                modifier = Modifier.width(100.dp),
            ) {
                Text(
                    text = "Make home",
                    color = Color.Gray,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(28.dp)
                )
                Text(
                    text = "BEAUTIFULL",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(30.dp)
                )
            }
            IconButton(onClick = { goToScreen("cart") }) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }

    data class Category(val name: String, val imageRes: Int)

    @Composable
    fun Categories() {
        val categories = listOf(
            Category("Popular", R.drawable.start),
            Category("Chair", R.drawable.chair),
            Category("Table", R.drawable.table),
            Category("Armchair", R.drawable.armchair),
            Category("Bed", R.drawable.bed),
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            items(categories) { category ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = category.imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = category.name,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun ProductGrid(goToScreen: (String) -> Unit) {
        val context = LocalContext.current
        var productList by remember {
            mutableStateOf(listOf<ProductModel>())
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
                .clickable {
                    goToScreen("detail/{}")
                },
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(productList.size) { product ->
                ProductItem(product = productList[product], goToScreen = {goToScreen(it)} )
            }
        }
        fun productsCallback(response: ProductResponseModel?) {
            if (response != null) {
                Log.d("TAG", "productsCallback: -------------------------")
                productList = response.data

            }
        }

        fun getProducts() {
            try {
                val api = RetrofitAPI()
                val limit = 10
                val page = 1
                api.getProductsByCategoryId(limit, page, ::productsCallback)
            } catch (e: Exception) {
                Toast.makeText(context, "Failed to get product", Toast.LENGTH_LONG).show()
            }
        }
        getProducts()
    }

    @Composable
    fun ProductItem(product: ProductModel, goToScreen: (String) -> Unit) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.images[0])
                .size(Size.ORIGINAL)
                .build()
        )
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable {
                    goToScreen("detail/${product._id}")
                }

        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = product.name,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "$${product.price}",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }

    data class Product(val id: Int, val name: String, val price: String, val imageRes: Int)
}