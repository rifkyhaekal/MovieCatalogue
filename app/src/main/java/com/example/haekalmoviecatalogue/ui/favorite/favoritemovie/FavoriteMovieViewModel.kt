package com.example.haekalmoviecatalogue.ui.favorite.favoritemovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = movieRepository.getFavoriteMovies()

}