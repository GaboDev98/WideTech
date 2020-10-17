package com.gabodev.widetech

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("Login")
    fun login(@Body jsonObject: JsonObject): Call<LoginResponse>

    @POST("GetProductsData")
    fun getProducts(): Call<ArrayList<ProductResponse>>
}