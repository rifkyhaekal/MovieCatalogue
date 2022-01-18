package com.example.haekalmoviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.source.MovieRepository
import com.example.haekalmoviecatalogue.data.source.remote.response.TvShowItem

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getPopularTvShows(): LiveData<List<TvShowItem>> = movieRepository.getPopularTvShow()

}