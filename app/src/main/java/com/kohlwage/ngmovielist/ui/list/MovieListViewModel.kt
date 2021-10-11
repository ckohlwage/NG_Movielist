package com.kohlwage.ngmovielist.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kohlwage.ngmovielist.models.Movie
import com.kohlwage.ngmovielist.repositories.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel@Inject constructor(
    movieListDataSource: MovieListRepository
    ): ViewModel() {

    val movieList: Flow<PagingData<Movie>> =
        movieListDataSource.getLatestMovies().cachedIn(viewModelScope)

}