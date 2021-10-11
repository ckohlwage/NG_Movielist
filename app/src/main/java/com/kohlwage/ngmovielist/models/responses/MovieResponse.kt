package com.kohlwage.ngmovielist.models.responses

import com.kohlwage.ngmovielist.models.*

data class MovieResponse(
    val id: Int,
    val budget: Int,
    val genres: List<Genres>,
    val original_language: String,
    val original_title: String,
    val overview: String?,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompanies>,
    val production_countries: List<ProductionCountries>,
    val release_date: String,
    val revenue: Int?,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguages>,
    val tagline: String?,
    val title: String,
    val vote_average: Double,
    val vote_count: Int,
)

fun MovieResponse.asMovie(): Movie = Movie(
    id = id.toString(),
    budget = budget,
    genres = genres ?: emptyList(),
    originalLanguage = original_language,
    originalTitle = original_title,
    overview = overview.orEmpty(),
    popularity = popularity,
    posterToken = poster_path,
    productionCompanies = production_companies ?: emptyList(),
    productionCountries = production_countries ?: emptyList(),
    releaseDate = release_date,
    revenue = revenue,
    runtime = runtime ?: 0,
    spokenLanguages = spoken_languages ?: emptyList(),
    tagline = tagline.orEmpty(),
    title = title,
    voteAverage = vote_average,
    voteCount = vote_count,
)
