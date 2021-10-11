package com.kohlwage.ngmovielist.di

import com.kohlwage.ngmovielist.pictures.GlideLoader
import com.kohlwage.ngmovielist.pictures.PictureLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface PictureModule {

    @Binds
    @Singleton
    fun bindPictureLoader(pictureLoader: GlideLoader): PictureLoader

}