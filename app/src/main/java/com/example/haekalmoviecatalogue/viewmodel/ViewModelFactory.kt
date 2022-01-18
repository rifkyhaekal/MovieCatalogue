package com.example.haekalmoviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haekalmoviecatalogue.data.source.MovieRepository
import com.example.haekalmoviecatalogue.di.Injection
import com.example.haekalmoviecatalogue.ui.detail.moviedetail.MovieDetailViewModel
import com.example.haekalmoviecatalogue.ui.detail.tvshowdetail.TvShowDetailViewModel
import com.example.haekalmoviecatalogue.ui.movie.MovieViewModel
import com.example.haekalmoviecatalogue.ui.tvshow.TvShowViewModel
import com.example.haekalmoviecatalogue.utils.JsonHelper

class ViewModelFactory private constructor(private val mMovieRepository: MovieRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mMovieRepository) as T
            }
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> {
                MovieDetailViewModel(mMovieRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mMovieRepository) as T
            }
            modelClass.isAssignableFrom(TvShowDetailViewModel::class.java) -> TvShowDetailViewModel(mMovieRepository) as T
            else -> throw IllegalArgumentException("Unknown view model class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(helper: JsonHelper): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injection.provideRepository(helper)).apply {
                    INSTANCE = this
                }
            }
    }
}