package com.example.haekalmoviecatalogue.ui.favorite.favoritetvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getFavoriteTvShow(): LiveData<List<TvShowEntity>> = movieRepository.getFavoriteTvShows()

}