package com.heyproject.core.di

import androidx.room.Room
import com.heyproject.core.BuildConfig
import com.heyproject.core.core.BASE_URL
import com.heyproject.core.core.TIMEOUT_CONNECTION
import com.heyproject.core.data.local.LocalDataSource
import com.heyproject.core.data.local.database.MoviesDatabase
import com.heyproject.core.data.remote.MovieApi
import com.heyproject.core.data.remote.RemoteDataSource
import com.heyproject.core.data.repository.MovieRepositoryImpl
import com.heyproject.core.data.utils.AppExecutors
import com.heyproject.core.domain.repository.MovieRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MoviesDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java, "MovieDB.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val loggingInterceptor =
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
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
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<MovieRepository> { MovieRepositoryImpl(get(), get(), get()) }
}