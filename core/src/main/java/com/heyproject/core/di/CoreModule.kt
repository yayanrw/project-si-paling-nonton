package com.heyproject.core.di

import androidx.room.Room
import com.heyproject.core.BuildConfig
import com.heyproject.core.core.BASE_URL
import com.heyproject.core.core.DB_NAME
import com.heyproject.core.core.HOST_NAME
import com.heyproject.core.core.TIMEOUT_CONNECTION
import com.heyproject.core.data.local.LocalDataSource
import com.heyproject.core.data.local.database.MoviesDatabase
import com.heyproject.core.data.remote.MovieApi
import com.heyproject.core.data.remote.RemoteDataSource
import com.heyproject.core.data.repository.MovieRepositoryImpl
import com.heyproject.core.data.utils.AppExecutors
import com.heyproject.core.domain.repository.MovieRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.SQLITE_KEY.toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java, DB_NAME
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val certificatePinner = CertificatePinner.Builder()
            .add(HOST_NAME, BuildConfig.SERTIFICATE_KEY_1)
            .add(HOST_NAME, BuildConfig.SERTIFICATE_KEY_2)
            .add(HOST_NAME, BuildConfig.SERTIFICATE_KEY_3)
            .add(HOST_NAME, BuildConfig.SERTIFICATE_KEY_4)
            .build()
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
            .certificatePinner(certificatePinner)
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