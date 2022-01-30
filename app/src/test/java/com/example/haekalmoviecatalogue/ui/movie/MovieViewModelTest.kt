package com.example.haekalmoviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.utils.DataDummy
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieEntity>>>


    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovies = Resource.success(DataDummy.generateDummyPopularMovies())
        val movies = MutableLiveData<Resource<List<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(movieRepository.getAllMovies()).thenReturn(movies)
        val movieEntities = viewModel.getPopularMovies().value?.data
        verify(movieRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getPopularMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}