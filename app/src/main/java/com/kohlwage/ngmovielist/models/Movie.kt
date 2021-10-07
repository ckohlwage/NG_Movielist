package com.kohlwage.ngmovielist.models

data class Movie(
    val id: Int,
    val title: String,
    val tagline: String,
    val runtime: Int,
    val posterPath: String?,
    val voteAverage: Float,
    val voteCount: Int,
)
