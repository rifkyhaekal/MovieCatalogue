package com.example.haekalmoviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.source.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieItemEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.PopularMovieEntity
import com.example.haekalmoviecatalogue.data.source.remote.response.MovieItem

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getPopularMovies(): LiveData<List<MovieItemEntity>> = movieRepository.getPopularMovie()

}