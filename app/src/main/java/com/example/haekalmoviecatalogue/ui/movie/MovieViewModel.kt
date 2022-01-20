package com.example.haekalmoviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.source.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieItemEntity

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getPopularMovies(): LiveData<List<MovieItemEntity>> = movieRepository.getPopularMovies()

}