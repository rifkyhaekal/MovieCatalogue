package com.example.haekalmoviecatalogue.ui.detail.tvshowdetail

import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.TvShowEntity
import com.example.haekalmoviecatalogue.utils.DataDummy

class DetailTvShowViewModel : ViewModel() {
    private lateinit var tvShowId: String

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): TvShowEntity {
        lateinit var tvShow: TvShowEntity
        val tvShowEntities = DataDummy.generateDummyTvShows()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.tvShowId == tvShowId) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }
}