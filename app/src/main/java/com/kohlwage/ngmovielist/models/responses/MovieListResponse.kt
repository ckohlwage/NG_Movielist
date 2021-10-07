package com.kohlwage.ngmovielist.models.responses

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val total_results: Int,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("results")
    val results: List<MovieResponse>,
)
