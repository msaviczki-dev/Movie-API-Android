package com.freecast.thatmovieapp.network.core

import com.freecast.thatmovieapp.BuildConfig

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 60L

class ApiBuilder {
    val client: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BuildConfig.API_SERVER_URL)
            .client(okHttpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private val okHttpBuilder by lazy {
        OkHttpClient.Builder().readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
    }
}