package com.heyproject.sipalingnonton.di

import androidx.room.Room
import com.heyproject.sipalingnonton.core.BASE_URL
import com.heyproject.sipalingnonton.data.local.LocalDataSource
import com.heyproject.sipalingnonton.data.local.database.MoviesDatabase
import com.heyproject.sipalingnonton.data.remote.MovieApi
import com.heyproject.sipalingnonton.data.remote.RemoteDataSource
import com.heyproject.sipalingnonton.data.repository.MovieRepositoryImpl
import com.heyproject.sipalingnonton.data.utils.AppExecutors
import com.heyproject.sipalingnonton.domain.repository.MovieRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MoviesDatabase>().movieDao() }
    factory { get<MoviesDatabase>().movieDetailDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java, "MovieDB.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(MovieApi::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<MovieRepository> { MovieRepositoryImpl(get(), get(), get()) }
}