package com.example.catchthecat.data.api

import com.example.catchthecat.data.model.GetImageData
import com.example.catchthecat.BuildConfig
import okhttp3.OkHttpClient
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
    fun getImages(@Query("q") query: String): Call<GetImageData>
}

/**
 * Retrofit singleton.
 */
object ImgurApi {
    private const val authToken = BuildConfig.AUTH

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(authToken))
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java)
}