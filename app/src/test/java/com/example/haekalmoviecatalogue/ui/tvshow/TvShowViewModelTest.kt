package com.example.haekalmoviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.PopularTvShowEntity
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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PopularTvShowEntity>

    @Mock
    private lateinit var connectionLiveData: ConnectionLiveData

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(connectionLiveData, movieRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = DataDummy.generateDummyPopularTvShows(true)
        val tvShows = MutableLiveData<PopularTvShowEntity>()
        tvShows.value = dummyTvShow

        `when`(movieRepository.getPopularTvShow()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getPopularTvShows().value
        verify(movieRepository).getPopularTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities?.results?.size)

        viewModel.getPopularTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}