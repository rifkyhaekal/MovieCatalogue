package com.example.haekalmoviecatalogue.di

import com.example.haekalmoviecatalogue.data.source.MovieRepository
import com.example.haekalmoviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository() : MovieRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return MovieRepository.getInstance(remoteDataSource)
    }
}