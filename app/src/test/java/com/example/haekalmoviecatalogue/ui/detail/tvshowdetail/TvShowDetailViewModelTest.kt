package com.example.haekalmoviecatalogue.ui.detail.tvshowdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.utils.DataDummy
import com.example.haekalmoviecatalogue.vo.Resource
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
    private val DELTA = 1e-15


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
        detailViewModel.setSelectedTvShow(dummyTvShow.tvShowId)

        detailViewModel.tvShow.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShowWithDetail)
    }
}