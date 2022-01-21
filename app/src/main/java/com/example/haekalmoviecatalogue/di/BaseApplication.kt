package com.example.haekalmoviecatalogue.di

import android.app.Application
import com.example.haekalmoviecatalogue.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@BaseApplication)
            modules(listOf(remoteDataSourceModule, movieRepository,movieViewModelModule, tvShowViewModelModule, movieDetailViewModelModule, tvShowDetailViewModelModule))
        }
    }
}