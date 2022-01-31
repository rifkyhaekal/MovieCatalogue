package com.example.haekalmoviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.vo.Resource

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getPopularTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> = movieRepository.getAllTvShows(sort)

}