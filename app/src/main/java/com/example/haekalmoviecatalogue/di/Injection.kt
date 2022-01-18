package com.example.haekalmoviecatalogue.di

import com.example.haekalmoviecatalogue.data.source.MovieRepository
import com.example.haekalmoviecatalogue.data.source.remote.RemoteDataSource
import com.example.haekalmoviecatalogue.utils.JsonHelper

object Injection {
    fun provideRepository(helper: JsonHelper): MovieRepository {

        val remoteDataSource = RemoteDataSource.getInstance(helper)

        return MovieRepository.getInstance(remoteDataSource)
    }
}