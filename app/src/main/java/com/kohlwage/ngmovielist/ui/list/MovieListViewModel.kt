package com.kohlwage.ngmovielist.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kohlwage.ngmovielist.datasources.MovieListDataSource
import com.kohlwage.ngmovielist.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel@Inject constructor(
    movieListDataSource: MovieListDataSource
    ): ViewModel() {

    val movieList: Flow<PagingData<Movie>> =
        movieListDataSource.fetchLatestMovies().cachedIn(viewModelScope)

}