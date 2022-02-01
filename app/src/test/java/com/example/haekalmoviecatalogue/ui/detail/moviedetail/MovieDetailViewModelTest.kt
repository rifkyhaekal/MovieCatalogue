package com.example.haekalmoviecatalogue.ui.detail.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieDetailEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.utils.DataDummy
import com.example.haekalmoviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.never
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
    private val dummyMovie = DataDummy.generateDummyMovieWithDetail(true)
    private val movieId = dummyMovie.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieDetailObserver: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        detailViewModel = MovieDetailViewModel(movieRepository)
        detailViewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val dummyMovieWithDetail = Resource.success(DataDummy.generateDummyMovieWithDetail(true))
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovieWithDetail

        `when`(movieRepository.getMovieDetail(movieId)).thenReturn(movie)

        val movieEntity = detailViewModel.movie.value?.data
        verify(movieRepository).getMovieDetail(movieId)
        assertNotNull(movieEntity)
        val movieDetailEntity = detailViewModel.movie.value?.data?.movieDetailEntity
        assertNotNull(movieDetailEntity)

        assertEquals(dummyMovie.movieId, movieEntity?.movieId)
        assertEquals(dummyMovie.imgPoster, movieEntity?.imgPoster)
        assertEquals(dummyMovie.favorite, movieEntity?.favorite)
        assertEquals(dummyMovie.movieDetailEntity?.title, movieEntity?.movieDetailEntity?.title)
        assertEquals(dummyMovie.movieDetailEntity?.duration, movieEntity?.movieDetailEntity?.duration)
        assertEquals(dummyMovie.movieDetailEntity?.genre, movieEntity?.movieDetailEntity?.genre)
        assertEquals(dummyMovie.movieDetailEntity?.overview, movieEntity?.movieDetailEntity?.overview)
        assertEquals(dummyMovie.movieDetailEntity?.releaseDate, movieEntity?.movieDetailEntity?.releaseDate)
        assertEquals(dummyMovie.movieDetailEntity?.status, movieEntity?.movieDetailEntity?.status)
        assertEquals(dummyMovie.movieDetailEntity?.userScore, movieEntity?.movieDetailEntity?.userScore)

        detailViewModel.movie.observeForever(movieDetailObserver)
        verify(movieDetailObserver).onChanged(dummyMovieWithDetail)
    }
}