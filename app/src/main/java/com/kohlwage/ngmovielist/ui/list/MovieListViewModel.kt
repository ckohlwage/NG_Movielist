package com.kohlwage.ngmovielist.ui.list

import android.content.Context
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kohlwage.ngmovielist.models.Movie
import com.kohlwage.ngmovielist.network.service.TmdbService
import com.kohlwage.ngmovielist.repositories.PagedMovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel@Inject constructor(
    @ApplicationContext @NonNull context: Context,
    private val service: TmdbService
    ): ViewModel() {

    val movieList: Flow<PagingData<Movie>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { PagedMovieListRepository(service, null) }
        ).flow.cachedIn(viewModelScope)

    val showEmptyState = false

    fun onMovieClick(movie: Movie) {

    }



}