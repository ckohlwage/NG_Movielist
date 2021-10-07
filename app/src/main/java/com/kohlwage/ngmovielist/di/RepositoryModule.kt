package com.kohlwage.ngmovielist.di

import com.kohlwage.ngmovielist.network.service.TmdbService
import com.kohlwage.ngmovielist.repositories.PagedMovieListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

/*    @Provides
    @Singleton
    fun provideMovieListRepository(service: TmdbService): PagedMovieListRepository =
        PagedMovieListRepository(service)*/

}