package com.example.haekalmoviecatalogue.ui.detail.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieDetailEntity
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
class MovieDetailViewModelTest {

    private lateinit var detailViewModel: MovieDetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovieDetail()
    private val movieId = dummyMovie.movieId
    private val DELTA = 1e-15

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieDetailObserver: Observer<MovieDetailEntity>

    @Before
    fun setUp() {
        detailViewModel = MovieDetailViewModel(movieRepository)
        detailViewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieDetailEntity>()
        movie.value = dummyMovie

        `when`(movieRepository.getMovieDetail(movieId)).thenReturn(movie)
        detailViewModel.setSelectedMovie(dummyMovie.movieId)
        val movieEntity = detailViewModel.getMovieDetail().value as MovieDetailEntity
        verify(movieRepository).getMovieDetail(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.genre, movieEntity.genre)
        assertEquals(dummyMovie.status, movieEntity.status)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.duration, movieEntity.duration)
        assertEquals(dummyMovie.userScore, movieEntity.userScore, DELTA)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.imgPoster, movieEntity.imgPoster)

        detailViewModel.getMovieDetail().observeForever(movieDetailObserver)
        verify(movieDetailObserver).onChanged(dummyMovie)
    }
}