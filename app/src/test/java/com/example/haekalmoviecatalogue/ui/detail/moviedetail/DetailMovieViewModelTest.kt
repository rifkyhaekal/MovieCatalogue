package com.example.haekalmoviecatalogue.ui.detail.moviedetail

import com.example.haekalmoviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.movieId

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovie.movieId)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.genre, movieEntity.genre)
        assertEquals(dummyMovie.status, movieEntity.status)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.duration, movieEntity.duration)
        assertEquals(dummyMovie.userScore, movieEntity.userScore)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.imgPoster, movieEntity.imgPoster)
    }
}