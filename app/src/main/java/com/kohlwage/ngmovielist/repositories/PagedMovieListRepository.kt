package com.kohlwage.ngmovielist.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kohlwage.ngmovielist.models.Movie
import com.kohlwage.ngmovielist.models.responses.asMovie
import com.kohlwage.ngmovielist.network.service.MovieDBService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PagedMovieListRepository @Inject constructor(
    private val service: MovieDBService,
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> =
        try {
            val nextPage = params.key ?: 1
            val response = service.fetchLatestMovies(page = nextPage)
            LoadResult.Page(
                data = response.results.map { it.asMovie() },
                prevKey = null,
                nextKey = response.page.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

}