package com.example.asm.helpers

import com.example.asm.httpmodels.LoginRequestModel
import com.example.asm.httpmodels.LoginResponseModel
import com.example.asm.httpmodels.ProductDeatilResponseModel
import com.example.asm.httpmodels.ProductResponseModel
import com.example.asm.httpmodels.RegisterRequestModel
import com.example.asm.httpmodels.RegisterResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface IRetrofit {
    //Khai báo các api dành cho app
    //http://localhost:6868/users/register
    @POST("users/register")
    fun register(@Body registerRequestModel: RegisterRequestModel): Call<RegisterResponseModel>


   // http://localhost:6868/users/login
    @POST("users/login")
    fun login(@Body body: LoginRequestModel): Call<LoginResponseModel>

    //API lấy danh sách sản phẩm
    //http://localhost:6868/products?limit=10&page=1
    @GET("products")
    fun getProductsByCategoryId(
        @Query("limit") limit: Int?,
        @Query("page") page: Int?
    ): Call<ProductResponseModel>

    //Lấy chi tiết sản phẩm
    //http://localhost:6868/products/660bb27797c632d7829160d1
    @GET("products/getProductById/{id}")
    fun getProductById(@Path("id") id: String?): Call<ProductDeatilResponseModel>

}