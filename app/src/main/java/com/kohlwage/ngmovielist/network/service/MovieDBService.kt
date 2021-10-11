package com.kohlwage.ngmovielist.network.service

import com.kohlwage.ngmovielist.models.responses.MovieListResponse
import com.kohlwage.ngmovielist.models.responses.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBService {

    @GET("discover/movie")
    suspend fun fetchLatestMovies(
        @Query("page") page: Int = 1,
        @Query("sort_by") sorting: String = "release_date.desc",
    ): MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieId: String,
    ): MovieResponse

}