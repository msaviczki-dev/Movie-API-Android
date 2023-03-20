package com.freecast.thatmovieapp.data.response

import com.google.gson.annotations.SerializedName

data class TVResponse(
    @SerializedName("results") val result: List<TVDataResponse>?
) {
    data class TVDataResponse(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val title: String?,
        @SerializedName("vote_average")
        val vote: Float?,
        @SerializedName("poster_path")
        val poster: String?,
        @SerializedName("genre_ids")
        val genreIds: List<Int>?
    )
}