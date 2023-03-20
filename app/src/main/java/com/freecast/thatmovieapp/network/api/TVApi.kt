package com.freecast.thatmovieapp.network.api

import com.freecast.thatmovieapp.data.response.TVGenreResponse
import com.freecast.thatmovieapp.data.response.TVResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TVApi {
    @GET("tv/top_rated")
    suspend fun getTV(@Query("api_key") key: String): Response<TVResponse>

    @GET("genre/tv/list")
    suspend fun getTVGenres(@Query("api_key") key: String): Response<TVGenreResponse>

}