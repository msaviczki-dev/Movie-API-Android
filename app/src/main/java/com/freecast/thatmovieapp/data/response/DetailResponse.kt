package com.freecast.thatmovieapp.data.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName("original_title") val title: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("backdrop_path") val poster: String?,
    @SerializedName("vote_average") val vote: Float?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("production_companies") val companies: List<ProductionCompaniesResponse>?
) {
    data class ProductionCompaniesResponse(
        @SerializedName("logo_path") val logo: String?, @SerializedName("name") val name: String?
    )
}