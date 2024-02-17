package com.example.catchthecat.data.api

import com.example.catchthecat.data.model.GetImageData
import com.example.catchthecat.data.model.ImageData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://api.imgur.com"

/**
 * Endpoints.
 */
interface RetrofitService {
    @GET("3/gallery/search")
    fun getImages(@Header("Authorization") auth: String, @Query("q") query: String): Call<GetImageData>
}

/**
 * Singleton do retrofit.
 */
object ReceiptApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java)
}