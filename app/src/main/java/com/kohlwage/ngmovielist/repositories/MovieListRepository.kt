package com.kohlwage.ngmovielist.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kohlwage.ngmovielist.models.Movie
import com.kohlwage.ngmovielist.network.service.TmdbService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListRepository @Inject constructor(private val service: TmdbService) {

    private val NETWORK_PAGE_SIZE = 20
    private val NETWORK_MAX_SIZE = 10000

    fun getLatestMovies(): Flow<PagingData<Movie>> =
        Pager(
            PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                maxSize = NETWORK_MAX_SIZE,
                prefetchDistance = NETWORK_PAGE_SIZE * 2
            )
        ) {
            PagedMovieListRepository(service, null)
        }.flow


}