package com.freecast.thatmovieapp.domain.entities

data class MovieDetailData(
    val title: String = "",
    val vote: Float = 0.0f,
    val overview: String = "",
    val companies: List<ProductionCompaniesData> = listOf()
) {
    data class ProductionCompaniesData(
        val logo: String = "",
        val name: String = ""
    )
}