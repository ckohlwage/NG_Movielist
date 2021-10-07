package com.kohlwage.ngmovielist.models.responses

import com.google.gson.annotations.SerializedName
import com.kohlwage.ngmovielist.models.Movie

data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("original_title")
    val original_title: String,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("vote_average")
    val vote_average: Float,
    @SerializedName("vote_count")
    val vote_count: Int,
    @SerializedName("release_date")
    val release_date: String,
)

fun MovieResponse.asMovie(): Movie = Movie(
    id = id,
    title = title,
    tagline = tagline.orEmpty(),
    runtime = runtime?: 0,
    posterPath = poster_path,
    voteAverage = vote_average,
    voteCount = vote_count,
)