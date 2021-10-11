package com.kohlwage.ngmovielist.repositories

import com.kohlwage.ngmovielist.models.responses.MovieResponse
import com.kohlwage.ngmovielist.network.service.MovieDBService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException

class MovieDetailRepositoryTest {

    private val service: MovieDBService = mock()

    private lateinit var repository: MovieDetailRepository

    @Before
    fun setUp() {
        repository = MovieDetailRepository(service)
    }

    @Test
    fun `GIVEN call successful WHEN fetchMovieDetails THEN return movie`() {
        runBlocking {
            whenever(service.fetchMovieDetails(any())).thenReturn(mockMovieResponse)

            val result = repository.fetchMovieDetail("id")

            assertNotNull(result)
        }
    }

    @Test
    fun `GIVEN call error WHEN fetchMovieDetails THEN return null`() {
        runBlocking {
            val exception: HttpException = mock()
            whenever(service.fetchMovieDetails(any())).thenThrow(exception)

            val result = repository.fetchMovieDetail("id")

            assertNull(result)
        }
    }

    private val mockMovieResponse = MovieResponse(
        123, 0, emptyList(), "",
        "", "", 0.0, "", emptyList(), emptyList(),
        "", 0, null, emptyList(), null, "title",
        0.0, 1
    )
}