package com.example.haekalmoviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.utils.ConnectionLiveData
import com.example.haekalmoviecatalogue.vo.Resource

class MovieViewModel(
    private val connectionLiveData: ConnectionLiveData,
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>> = movieRepository.getAllMovies()

    fun internetConnection(): ConnectionLiveData = connectionLiveData

}