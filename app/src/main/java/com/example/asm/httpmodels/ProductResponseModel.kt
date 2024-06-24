package com.example.asm.httpmodels

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date


data class ProductModel(
    @JsonProperty("_id") val _id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("price") val price: Int,
    @JsonProperty("quantity") val quantity: Int,
    @JsonProperty("images") val images: List<String>,
    @JsonProperty("description") val description: String,
    @JsonProperty("category") val category: CategoryItem,

)
data class ProductModell(
    @JsonProperty("_id") val _id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("price") val price: Int,
    @JsonProperty("quantity") val quantity: Int,
    @JsonProperty("images") val images: List<String>,
    @JsonProperty("description") val description: String,
    @JsonProperty("category") val category: CategoryItem,
    @JsonProperty("createdAt") val createdAt: Date,
    @JsonProperty("updateAt") val updateAt: Date,
    @JsonProperty("__v") val __v: Int,


    )
data class CategoryItem(
    @JsonProperty("category_id") val category_id: String,
    @JsonProperty("category_name") val category_name: String,
)


//Lấy danh sách sản phẩm
data class ProductResponseModel(
    @JsonProperty("status") val status: Boolean,
    @JsonProperty("data") val data: List<ProductModel>
)

//Lấy chi tiết
data class ProductDeatilResponseModel(
    @JsonProperty("status") val status: Boolean,
    @JsonProperty("data") val data: ProductModell
)