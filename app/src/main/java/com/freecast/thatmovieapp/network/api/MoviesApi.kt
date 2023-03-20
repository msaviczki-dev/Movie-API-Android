package com.freecast.thatmovieapp.network.api

import com.freecast.thatmovieapp.data.response.MovieResponse
import com.freecast.thatmovieapp.data.response.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/top_rated")
    suspend fun getMovies(@Query("api_key") key: String): Response<MovieResponse>
}