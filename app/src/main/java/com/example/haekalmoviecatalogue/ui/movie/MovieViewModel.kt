package com.example.haekalmoviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.source.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.PopularMovieEntity
import com.example.haekalmoviecatalogue.utils.ConnectionLiveData

class MovieViewModel(
    private val connectionLiveData: ConnectionLiveData,
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getPopularMovies(): LiveData<PopularMovieEntity> = movieRepository.getPopularMovies()

    fun internetConnection(): ConnectionLiveData = connectionLiveData

}