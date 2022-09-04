package com.heyproject.sipalingnonton.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.heyproject.sipalingnonton.core.BASE_URL
import com.heyproject.sipalingnonton.data.local.LocalDataSource
import com.heyproject.sipalingnonton.data.local.database.MoviesDatabase
import com.heyproject.sipalingnonton.data.remote.MovieApi
import com.heyproject.sipalingnonton.data.remote.RemoteDataSource
import com.heyproject.sipalingnonton.data.repository.MovieRepositoryImpl
import com.heyproject.sipalingnonton.data.util.GsonParser
import com.heyproject.sipalingnonton.domain.repository.MovieRepository
import com.heyproject.sipalingnonton.domain.usecase.GetMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGetMoviesUsecase(repository: MovieRepository): GetMovies {
        return GetMovies(repository)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideMoviesDatabase(app: Application): MoviesDatabase {
        return Room.databaseBuilder(
            app, MoviesDatabase::class.java,
            "movies_db"
        )
            .addTypeConverter(
                GsonParser(
                    Gson()
                )
            ).build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}