package com.example.asm.models

import com.example.asm.httpmodels.ProductModell

data class Cart(
    val product: ProductModell,
    val quantity: Int,
)
