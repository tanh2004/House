package com.example.asm.httpmodels

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

data class UserModel(
    @JsonProperty("_id") val id: String?,
    @JsonProperty("email") val email: String?,
    @JsonProperty("name") val name: String?,
    @JsonProperty("role") val role: Int?,
    @JsonProperty("carts") val carts: Any?,
)

data class LoginResponseModel(
    @JsonProperty("status") val status: Boolean?,
    @JsonProperty("data") val data: UserModel?,
)

