package com.example.haekalmoviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.PopularMovieEntity
import com.example.haekalmoviecatalogue.utils.ConnectionLiveData
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PopularMovieEntity>

    @Mock
    private lateinit var connectionLiveData: ConnectionLiveData

    @Before
    fun setUp() {
        viewModel = MovieViewModel(connectionLiveData, movieRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovies = DataDummy.generateDummyPopularMovies(true)
        val movies = MutableLiveData<PopularMovieEntity>()
        movies.value = dummyMovies

        `when`(movieRepository.getPopularMovies()).thenReturn(movies)
        val movieEntities = viewModel.getPopularMovies().value
        verify(movieRepository).getPopularMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.results?.size)

        viewModel.getPopularMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}