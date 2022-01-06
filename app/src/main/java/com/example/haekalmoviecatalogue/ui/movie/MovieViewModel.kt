package com.example.haekalmoviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.MovieEntity
import com.example.haekalmoviecatalogue.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovies(): List<MovieEntity> = DataDummy.generateDummyMovies()
}