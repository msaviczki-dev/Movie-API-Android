package com.freecast.thatmovieapp.domain.entities

data class DetailData(
    val title: String = "",
    val tvTitle: String = "",
    val poster: String = "",
    val vote: Float = 0.0f,
    val voteRate: Float = 0.0f,
    val overview: String = "",
    val companies: List<ProductionCompaniesData> = listOf()
) {
    data class ProductionCompaniesData(
        val logo: String = "",
        val name: String = ""
    )
}