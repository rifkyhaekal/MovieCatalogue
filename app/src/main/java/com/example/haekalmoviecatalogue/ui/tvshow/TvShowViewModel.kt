package com.example.haekalmoviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.utils.ConnectionLiveData
import com.example.haekalmoviecatalogue.vo.Resource

class TvShowViewModel(
    private val connectionLiveData: ConnectionLiveData,
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getPopularTvShows(): LiveData<Resource<List<TvShowEntity>>> = movieRepository.getAllTvShows()

    fun internetConnection(): ConnectionLiveData = connectionLiveData

}