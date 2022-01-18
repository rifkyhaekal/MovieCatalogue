package com.example.haekalmoviecatalogue.ui.detail.tvshowdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.source.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowDetailEntity

class TvShowDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private var tvShowId: Int? = null

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getTvShowDetail(): LiveData<TvShowDetailEntity> = movieRepository.getTvShowDetail(tvShowId)

}