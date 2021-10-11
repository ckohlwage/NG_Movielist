package com.kohlwage.ngmovielist.datasources

import androidx.paging.PagingData
import com.kohlwage.ngmovielist.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieListDataSource {

    fun fetchLatestMovies(): Flow<PagingData<Movie>>

}