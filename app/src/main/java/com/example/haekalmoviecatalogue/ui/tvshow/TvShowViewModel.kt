package com.example.haekalmoviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.source.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.PopularTvShowEntity
import com.example.haekalmoviecatalogue.utils.ConnectionLiveData

class TvShowViewModel(
    private val connectionLiveData: ConnectionLiveData,
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getPopularTvShows(): LiveData<PopularTvShowEntity> = movieRepository.getPopularTvShow()

    fun internetConnection(): ConnectionLiveData = connectionLiveData

}