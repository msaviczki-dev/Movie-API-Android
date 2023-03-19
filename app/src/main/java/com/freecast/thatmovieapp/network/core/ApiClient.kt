package com.freecast.thatmovieapp.network.core

import retrofit2.Retrofit

class ApiClient {
    val client: Retrofit by lazy {
        ApiBuilder().client
    }
}