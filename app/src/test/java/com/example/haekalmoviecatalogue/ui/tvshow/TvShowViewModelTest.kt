package com.example.haekalmoviecatalogue.ui.tvshow

import com.example.haekalmoviecatalogue.ui.movie.MovieViewModel
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShowEntities = viewModel.getTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)
    }
}