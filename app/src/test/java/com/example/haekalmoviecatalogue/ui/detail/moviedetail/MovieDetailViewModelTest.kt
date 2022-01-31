package com.example.haekalmoviecatalogue.ui.detail.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
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
class MovieDetailViewModelTest {

    private lateinit var detailViewModel: MovieDetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovieWithDetail(true)
    private val movieId = dummyMovie.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieDetailObserver: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        detailViewModel = MovieDetailViewModel(movieRepository)
        detailViewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val dummyMovieWithDetail = Resource.success(dummyMovie)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovieWithDetail

        `when`(movieRepository.getMovieDetail(movieId)).thenReturn(movie)

        detailViewModel.movie.observeForever(movieDetailObserver)
        verify(movieDetailObserver).onChanged(dummyMovieWithDetail)
    }
}