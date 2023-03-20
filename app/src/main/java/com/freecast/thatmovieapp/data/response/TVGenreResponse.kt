package com.freecast.thatmovieapp.data.response

import com.google.gson.annotations.SerializedName

data class TVGenreResponse(
    @SerializedName("genres") val genres: List<GenreDataResponse>?
) {
    data class GenreDataResponse(
        @SerializedName("id") val id: Int?, @SerializedName("name") val name: String?
    )
}