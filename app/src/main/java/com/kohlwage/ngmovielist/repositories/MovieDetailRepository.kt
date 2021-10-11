package com.kohlwage.ngmovielist.repositories

import com.kohlwage.ngmovielist.datasources.MovieDetailDataSource
import com.kohlwage.ngmovielist.models.Movie
import com.kohlwage.ngmovielist.models.responses.asMovie
import com.kohlwage.ngmovielist.network.service.MovieDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    private val movieDBService: MovieDBService
) : MovieDetailDataSource {

    override suspend fun fetchMovieDetail(movieId: String): Movie? {
        return withContext(Dispatchers.IO) {
            try {
                val result = movieDBService.fetchMovieDetails(movieId = movieId)
                result.asMovie()
            } catch (e: Exception) {
                null
            }
        }
    }
}