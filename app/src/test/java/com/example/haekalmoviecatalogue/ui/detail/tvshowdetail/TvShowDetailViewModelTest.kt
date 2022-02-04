package com.example.haekalmoviecatalogue.ui.detail.tvshowdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.utils.DataDummy
import com.example.haekalmoviecatalogue.utils.LiveDataTestUtil
import com.example.haekalmoviecatalogue.vo.Resource
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
    private val dummyTvShow = DataDummy.generateDummyTvShowWithDetail(true)
    private val tvShowId = dummyTvShow.tvShowId


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        detailViewModel = TvShowDetailViewModel(movieRepository)
        detailViewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getTvShow() {
        val dummyTvShowWithDetail = Resource.success(dummyTvShow)
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShowWithDetail

        `when`(movieRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)

        detailViewModel.tvShow.observeForever(tvShowObserver)

        val tvShowEntity = LiveDataTestUtil.getValue(detailViewModel.tvShow)
        verify(movieRepository).getTvShowDetail(tvShowId)
        assertNotNull(tvShowEntity)
        val tvShowDetailEntity = LiveDataTestUtil.getValue(detailViewModel.tvShow)
        assertNotNull(tvShowDetailEntity.data?.tvShowDetailEntity)

        assertEquals(dummyTvShow.tvShowId, tvShowEntity?.data?.tvShowId)
        assertEquals(dummyTvShow.imgPoster, tvShowEntity?.data?.imgPoster)
        assertEquals(dummyTvShow.favorite, tvShowEntity?.data?.favorite)
        assertEquals(dummyTvShow.tvShowDetailEntity?.title, tvShowEntity?.data?.tvShowDetailEntity?.title)
        assertEquals(dummyTvShow.tvShowDetailEntity?.type, tvShowEntity?.data?.tvShowDetailEntity?.type)
        assertEquals(dummyTvShow.tvShowDetailEntity?.genre, tvShowEntity?.data?.tvShowDetailEntity?.genre)
        assertEquals(dummyTvShow.tvShowDetailEntity?.overview, tvShowEntity?.data?.tvShowDetailEntity?.overview)
        assertEquals(dummyTvShow.tvShowDetailEntity?.network, tvShowEntity?.data?.tvShowDetailEntity?.network)
        assertEquals(dummyTvShow.tvShowDetailEntity?.status, tvShowEntity?.data?.tvShowDetailEntity?.status)
        assertEquals(dummyTvShow.tvShowDetailEntity?.userScore, tvShowEntity?.data?.tvShowDetailEntity?.userScore)

        verify(tvShowObserver).onChanged(dummyTvShowWithDetail)
        detailViewModel.tvShow.removeObserver(tvShowObserver)
    }
}