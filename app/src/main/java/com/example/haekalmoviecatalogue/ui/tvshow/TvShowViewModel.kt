package com.example.haekalmoviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.data.TvShowEntity
import com.example.haekalmoviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShows(): List<TvShowEntity> = DataDummy.generateDummyTvShows()
}