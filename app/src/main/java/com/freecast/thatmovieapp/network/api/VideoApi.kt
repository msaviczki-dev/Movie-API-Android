package com.freecast.thatmovieapp.network.api

import com.freecast.thatmovieapp.data.response.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoApi {
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideo(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String
    ): Response<VideoResponse>

    @GET("tv/{tv_id}/videos")
    suspend fun getTVVideo(
        @Path("tv_id") tvId: Int,
        @Query("api_key") key: String
    ): Response<VideoResponse>
}