package com.example.haekalmoviecatalogue.ui.detail.tvshowdetail

import com.example.haekalmoviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowDetailViewModelTest {

    private lateinit var detailViewModel: TvShowDetailViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.tvShowId

    @Before
    fun setUp() {
        detailViewModel = TvShowDetailViewModel()
        detailViewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getTvShow() {
        detailViewModel.setSelectedTvShow(dummyTvShow.tvShowId)
        val movieEntity = detailViewModel.getTvShow()
        assertNotNull(movieEntity)
        assertEquals(dummyTvShow.tvShowId, movieEntity.tvShowId)
        assertEquals(dummyTvShow.title, movieEntity.title)
        assertEquals(dummyTvShow.genre, movieEntity.genre)
        assertEquals(dummyTvShow.status, movieEntity.status)
        assertEquals(dummyTvShow.network, movieEntity.network)
        assertEquals(dummyTvShow.type, movieEntity.type)
        assertEquals(dummyTvShow.userScore, movieEntity.userScore)
        assertEquals(dummyTvShow.overview, movieEntity.overview)
        assertEquals(dummyTvShow.imgPoster, movieEntity.imgPoster)
    }
}