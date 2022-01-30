package com.example.haekalmoviecatalogue.ui.favorite.favoritetvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = movieRepository.getFavoriteTvShows()

}