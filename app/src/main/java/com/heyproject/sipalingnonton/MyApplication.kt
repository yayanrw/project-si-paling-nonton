package com.heyproject.sipalingnonton

import android.app.Application
import com.heyproject.core.di.*
import com.heyproject.sipalingnonton.di.usecaseModule
import com.heyproject.sipalingnonton.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    usecaseModule,
                    viewModelModule
                )
            )
        }
    }
}