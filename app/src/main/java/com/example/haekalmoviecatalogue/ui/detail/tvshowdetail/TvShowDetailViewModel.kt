package com.example.haekalmoviecatalogue.ui.detail.tvshowdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.vo.Resource

class TvShowDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val tvShowId = MutableLiveData<Int>()

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    var tvShow: LiveData<Resource<TvShowEntity>> = Transformations.switchMap(tvShowId) { mTvShowId ->
        movieRepository.getTvShowDetail(mTvShowId)
    }

    fun setFavorite() {
        val tvShowResource = tvShow.value
        if (tvShowResource != null) {
            val tvShowDetail = tvShowResource.data

            if (tvShowDetail != null) {
                val tvShowEntity = tvShowDetail
                val newState = !tvShowEntity.favorite
                movieRepository.setTvShowFavorite(tvShowEntity, newState)
            }
        }
    }

}