package com.example.haekalmoviecatalogue.ui.detail.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.source.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieDetailEntity

class MovieDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private var movieId: Int? = null

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getMovieDetail(): LiveData<MovieDetailEntity> = movieRepository.getMovieDetail(movieId)
}