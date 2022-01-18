package com.example.haekalmoviecatalogue.ui.detail.moviedetail

import com.example.haekalmoviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieDetailViewModelTest {

    private lateinit var detailViewModel: MovieDetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.movieId

    @Before
    fun setUp() {
        detailViewModel = MovieDetailViewModel()
        detailViewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        detailViewModel.setSelectedMovie(dummyMovie.movieId)
        val movieEntity = detailViewModel.getMovie()
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