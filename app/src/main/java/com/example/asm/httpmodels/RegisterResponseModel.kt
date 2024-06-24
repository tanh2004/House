package com.example.asm.httpmodels

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

data class RegisterResponseModel(
    @JsonProperty("status") val status: Boolean?,
    @JsonProperty("data") val data: User?,
)
data class User(
    @JsonProperty("name") val name: String?,
    @JsonProperty("email") val email: String?,
    @JsonProperty("password") val password: String?,
    @JsonProperty("role") val role: Int?,
    @JsonProperty("carts") val carts: List<ItemCarts>?,
    @JsonProperty("isVerified") val isVerified: Int?,
    @JsonProperty("available") val available: Boolean?,
    @JsonProperty("_id") val id: String?,
    @JsonProperty("createdAt") val createdAt: Date?,
    @JsonProperty("updateAt") val updateAt: Date?,
    @JsonProperty("__v") val version: Int?
    )
data class ItemCarts(
    @JsonProperty("_id") val id: String?,
    @JsonProperty("date") val date: Date?,
    @JsonProperty("total") val total: Int?,
    @JsonProperty("status") val status: Boolean?,
    )
