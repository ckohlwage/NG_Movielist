package com.kohlwage.ngmovielist.models.responses

data class MovieListResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<MovieResponse>,
)
