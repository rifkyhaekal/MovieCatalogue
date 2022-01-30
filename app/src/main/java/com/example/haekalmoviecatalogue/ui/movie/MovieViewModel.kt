package com.example.haekalmoviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getAllMovies()

}