package com.freecast.thatmovieapp.data.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results") val result: List<MovieDataResponse>?
) {
    data class MovieDataResponse(
        @SerializedName("id") val id: Int?,
        @SerializedName("title") val title: String?,
        @SerializedName("poster_path") val poster: String?,
        @SerializedName("vote_average") val vote: Float?
    )
}