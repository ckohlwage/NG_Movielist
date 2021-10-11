package com.kohlwage.ngmovielist.di

import com.kohlwage.ngmovielist.BuildConfig
import com.kohlwage.ngmovielist.network.service.TmdbService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(ApiKeyInterceptor())
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            builder.addInterceptor(interceptor)
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.themoviedb.org/3/")
        .build()

    @Provides
    @Singleton
    fun provideTmdbService(retrofit: Retrofit): TmdbService = retrofit.create()


    inner class ApiKeyInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request();
            val url = original.url
            val newUrl = url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            val request = original.newBuilder().url(newUrl).build()
            return chain.proceed(request)
        }
    }
}