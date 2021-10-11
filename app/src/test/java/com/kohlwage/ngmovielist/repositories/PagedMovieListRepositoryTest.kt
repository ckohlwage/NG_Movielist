package com.kohlwage.ngmovielist.repositories

import androidx.paging.PagingSource
import com.kohlwage.ngmovielist.models.Movie
import com.kohlwage.ngmovielist.models.responses.MovieListResponse
import com.kohlwage.ngmovielist.models.responses.MovieResponse
import com.kohlwage.ngmovielist.network.service.MovieDBService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException

class PagedMovieListRepositoryTest {

    private val service: MovieDBService = mock()

    private lateinit var repository: PagedMovieListRepository

    @Before
    fun setUp() {
        repository = PagedMovieListRepository(service)
    }

    @Test
    fun `GIVEN call successful WHEN fetchMovieList THEN return LoadResult Page`() {
        runBlocking {
            val params: PagingSource.LoadParams<Int> = mock()
            whenever(params.key).thenReturn(1)
            whenever(
                service.fetchLatestMovies(
                    any(),
                    anyOrNull()
                )
            ).thenReturn(mockMovieListResponse)

            val result: PagingSource.LoadResult<Int, Movie> = repository.load(params)

            assert(result is PagingSource.LoadResult.Page)
            assertEquals(2, (result as PagingSource.LoadResult.Page).data.size)
        }
    }

    @Test
    fun `GIVEN call error WHEN fetchMovieDetails THEN return LoadResult Error`() {
        runBlocking {
            val exception: HttpException = mock()
            val params: PagingSource.LoadParams<Int> = mock()
            whenever(params.key).thenReturn(1)
            whenever(service.fetchLatestMovies(any(), anyOrNull())).thenThrow(exception)

            val result: PagingSource.LoadResult<Int, Movie> = repository.load(params)

            assert(result is PagingSource.LoadResult.Error)
        }
    }

    private val mockMovieResponse = MovieResponse(
        123, 0, emptyList(), "",
        "", "", 0.0, "", emptyList(), emptyList(),
        "", 0, null, emptyList(), null, "title",
        0.0, 1
    )

    private val mockMovieListResponse = MovieListResponse(
        1, 2, 4, listOf(
            mockMovieResponse, mockMovieResponse
        )
    )
}