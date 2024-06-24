package com.example.asm.httpmodels

import com.fasterxml.jackson.annotation.JsonProperty

//Model gửi đi dành cho đăng ký
data class RegisterRequestModel(
    @JsonProperty("name") val name: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String
)
