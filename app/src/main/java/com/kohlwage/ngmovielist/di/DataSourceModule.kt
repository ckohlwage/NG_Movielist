package com.kohlwage.ngmovielist.di

import com.kohlwage.ngmovielist.datasources.MovieDetailDataSource
import com.kohlwage.ngmovielist.datasources.MovieListDataSource
import com.kohlwage.ngmovielist.repositories.MovieDetailRepository
import com.kohlwage.ngmovielist.repositories.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DataSourceModule {

    @Binds
    @Singleton
    fun movieListDataSource(movieListDataSource: MovieListRepository): MovieListDataSource

    @Binds
    @Singleton
    fun movieDetailDataSource(movieDetailDataSource: MovieDetailRepository): MovieDetailDataSource
}