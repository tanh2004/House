package com.example.asm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.asm.ui.theme.ASMTheme

class HomeActivity : ComponentActivity() {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(20.dp)
        ) {
            TopBar()
            Categories()
            ProductGrid()
        }
    }

    @Composable
    fun TopBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
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
            Image(
                painter = painterResource(id = R.drawable.cart),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
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
    fun ProductGrid() {
        // This is a placeholder. You should replace with your own product data
        val products = listOf(
            Product(1,"Black Simple Lamp", "$ 12.00", R.drawable.img_1),
            Product(2,"Minimal Stand", "$ 25.00", R.drawable.img_4),
            Product(3,"Coffee Chair", "$ 20.00", R.drawable.img_3),
            Product(4,"Simple Desk", "$ 50.00", R.drawable.img_2),
            Product(5,"Coffee Chair", "$ 20.00", R.drawable.img_3),
            Product(6,"Simple Desk", "$ 50.00", R.drawable.img_2),
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products.size) { index ->
                ProductItem(products[index])
            }
        }
    }

    @Composable
    fun ProductItem(product: Product) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
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
                text = product.price,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }

    data class Product(val id: Int, val name: String, val price: String, val imageRes: Int)

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreView() {
        ASMTheme {
            Body()
        }
    }
}
