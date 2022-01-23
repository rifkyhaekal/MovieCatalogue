package com.example.haekalmoviecatalogue.ui.detail.tvshowdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.haekalmoviecatalogue.data.source.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.example.haekalmoviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowDetailViewModelTest {

    private lateinit var detailViewModel: TvShowDetailViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShowDetail()
    private val tvShowId = dummyTvShow.tvShowId
    private val DELTA = 1e-15


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var tvShowDetailObserver: Observer<TvShowDetailEntity>

    @Before
    fun setUp() {
        detailViewModel = TvShowDetailViewModel(movieRepository)
        detailViewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TvShowDetailEntity>()
        tvShow.value = dummyTvShow

        `when`(movieRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        detailViewModel.setSelectedTvShow(dummyTvShow.tvShowId)
        val movieEntity = detailViewModel.getTvShowDetail().value as TvShowDetailEntity
        verify(movieRepository).getTvShowDetail(tvShowId)
        assertNotNull(movieEntity)
        assertEquals(dummyTvShow.tvShowId, movieEntity.tvShowId)
        assertEquals(dummyTvShow.title, movieEntity.title)
        assertEquals(dummyTvShow.genre, movieEntity.genre)
        assertEquals(dummyTvShow.status, movieEntity.status)
        assertEquals(dummyTvShow.network, movieEntity.network)
        assertEquals(dummyTvShow.type, movieEntity.type)
        assertEquals(dummyTvShow.userScore, movieEntity.userScore, DELTA)
        assertEquals(dummyTvShow.overview, movieEntity.overview)
        assertEquals(dummyTvShow.imgPoster, movieEntity.imgPoster)

        detailViewModel.getTvShowDetail().observeForever(tvShowDetailObserver)
        verify(tvShowDetailObserver).onChanged(dummyTvShow)
    }
}