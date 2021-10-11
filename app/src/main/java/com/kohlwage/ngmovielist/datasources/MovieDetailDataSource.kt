package com.kohlwage.ngmovielist.datasources

import com.kohlwage.ngmovielist.models.Movie

interface MovieDetailDataSource {

    suspend fun fetchMovieDetail(movieId: String): Movie?

}