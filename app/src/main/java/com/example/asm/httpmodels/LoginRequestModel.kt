package com.example.asm.httpmodels

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginRequestModel(
    @JsonProperty("email") val email: String?,
    @JsonProperty("password") val password: String?,

    )
