package com.example.asm.helpers

import android.util.Log
import com.example.asm.httpmodels.LoginRequestModel
import com.example.asm.httpmodels.LoginResponseModel
import com.example.asm.httpmodels.ProductDeatilResponseModel
import com.example.asm.httpmodels.ProductResponseModel
import com.example.asm.httpmodels.RegisterRequestModel
import com.example.asm.httpmodels.RegisterResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitAPI {
    private val retrofit = RetrofitClient.getClient()
    private  val api = retrofit.create(IRetrofit::class.java)


    // Lấy chi tiết sản phẩm theo id
    //fun lấy chi tết sản phẩm
    fun getProductById(
        id: String?,
        callback: (ProductDeatilResponseModel?) -> Unit
    ){
        api.getProductById(id).enqueue(object : Callback<ProductDeatilResponseModel> {
            override fun onResponse(
                call: Call<ProductDeatilResponseModel>,
                response: Response<ProductDeatilResponseModel>
            ) {
                if (response.isSuccessful) {
                    val productDetailResponse = response.body()
                    callback(productDetailResponse)
                } else {
                    Log.d("Failed to get product detail", response.message())
                }
            }

            override fun onFailure(call: Call<ProductDeatilResponseModel>, t: Throwable) {
                Log.d("Failed to get product detail", t.message ?: "Unknown error")
            }
        })
    }
    //fun lấy danh sách sản phẩm
    fun getProductsByCategoryId(
        limit: Int?,
        page: Int?,
        callback: (ProductResponseModel?) -> Unit
    ){
        api.getProductsByCategoryId(limit, page).enqueue(object : Callback<ProductResponseModel> {
            override fun onResponse(
                call: Call<ProductResponseModel>,
                response: Response<ProductResponseModel>
            ) {
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    callback(productResponse)
                } else {
                    Log.d("Failed to get products", response.message())
                }
            }

            override fun onFailure(call: Call<ProductResponseModel>, t: Throwable) {
                Log.d("Failed to get products", t.message ?: "Unknown error")
            }
        })
    }

    fun login(
        body: LoginRequestModel,
        callback: (LoginResponseModel?) -> Unit
    ) {
        api.login(body).enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(
                call: Call<LoginResponseModel>,
                response: Response<LoginResponseModel>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    callback(result)
                } else {
                    Log.d("Failed to login", response.message())
                }
            }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                Log.d("Failed to login", t.message ?: "Unknown error")
            }
        })

    }

    fun register(
        user: RegisterRequestModel,
        callback: (RegisterResponseModel?) -> Unit
    ) {
        api.register(user).enqueue(object : Callback<RegisterResponseModel> {
            override fun onResponse(
                call: Call<RegisterResponseModel>,
                response: Response<RegisterResponseModel>
            ) {
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    callback(registerResponse)
                } else {
                    Log.d("Failed to register", response.message())
                }
            }

            override fun onFailure(call: Call<RegisterResponseModel>, t: Throwable) {
                Log.d("Failed to register", t.message ?: "Unknown error")
            }
        })

    }
}