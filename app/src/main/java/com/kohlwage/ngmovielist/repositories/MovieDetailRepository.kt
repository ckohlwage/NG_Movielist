package com.kohlwage.ngmovielist.repositories

import com.kohlwage.ngmovielist.models.Movie
import com.kohlwage.ngmovielist.models.responses.asMovie
import com.kohlwage.ngmovielist.network.service.TmdbService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(private val tmdbService: TmdbService) {

    suspend fun getMovieDetail(movieId: String): Movie? {
        return withContext(Dispatchers.IO) {
            try {
                val result = tmdbService.getMovieDetails(movieId = movieId)
                result.asMovie()
            } catch (e: Exception) {
                null
            }
        }
    }
}