package com.kohlwage.ngmovielist.models

data class Movie(
    val id: String,
    val budget: Int,
    val genres: List<Genres>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    val posterToken: String?,
    val productionCompanies: List<ProductionCompanies>,
    val productionCountries: List<ProductionCountries>,
    val releaseDate: String,
    val revenue: Int?,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguages>,
    val tagline: String?,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
)

interface ListWithNames {
    val name: String
}

data class Genres(
    val id: Int,
    override val name: String,
) : ListWithNames

data class ProductionCompanies(
    val id: Int,
    override val name: String,
    val logo_path: String?,
    val origin_country: String,
) : ListWithNames

data class ProductionCountries(
    val iso_3166_1: String,
    override val name: String,
) : ListWithNames

data class SpokenLanguages(
    val iso_639_1: String,
    override val name: String,
) : ListWithNames
