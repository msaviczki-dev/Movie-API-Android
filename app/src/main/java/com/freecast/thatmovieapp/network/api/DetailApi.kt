package com.freecast.thatmovieapp.network.api

import com.freecast.thatmovieapp.data.response.DetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailApi {
    @GET("tv/{tv_id}")
    suspend fun getTVDetail(
        @Path("tv_id") movieId: Int,
        @Query("api_key") key: String
    ): Response<DetailResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String
    ): Response<DetailResponse>
}