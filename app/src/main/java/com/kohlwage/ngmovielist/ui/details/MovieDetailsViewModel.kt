package com.kohlwage.ngmovielist.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kohlwage.ngmovielist.models.Movie
import com.kohlwage.ngmovielist.repositories.MovieDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailDataSource: MovieDetailRepository
) : ViewModel() {

    private var movieId: String = ""

    private val _movie: MutableLiveData<Movie?> = MutableLiveData(null)
    val movie: LiveData<Movie?>
        get() = _movie

    val isError = MutableLiveData(false)
    val isLoading = MutableLiveData(true)

    fun getMovieDetails(newMovieId: String) {
        if (movieId != newMovieId || movie.value == null) {
            reset()
            movieId = newMovieId
        }
        viewModelScope.launch {
            _movie.value = movieDetailDataSource.getMovieDetail(movieId)
            isError.value = _movie.value == null
            isLoading.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        reset()
    }

    private fun reset() {
        _movie.value = null
        isLoading.value = true
        isError.value = false
    }


}