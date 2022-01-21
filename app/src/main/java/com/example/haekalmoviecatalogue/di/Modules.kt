package com.example.haekalmoviecatalogue.di

import com.example.haekalmoviecatalogue.data.source.MovieRepository
import com.example.haekalmoviecatalogue.data.source.remote.RemoteDataSource
import com.example.haekalmoviecatalogue.ui.detail.moviedetail.MovieDetailViewModel
import com.example.haekalmoviecatalogue.ui.detail.tvshowdetail.TvShowDetailViewModel
import com.example.haekalmoviecatalogue.ui.movie.MovieViewModel
import com.example.haekalmoviecatalogue.ui.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { RemoteDataSource.getInstance() }
}

val movieRepository = module {
    single { MovieRepository.getInstance(get()) }
}

val movieViewModelModule = module {
    viewModel { MovieViewModel(get()) }
}

val tvShowViewModelModule = module {
    viewModel { TvShowViewModel(get()) }
}

val movieDetailViewModelModule = module {
    viewModel { MovieDetailViewModel(get()) }
}

val tvShowDetailViewModelModule = module {
    viewModel { TvShowDetailViewModel(get()) }
}