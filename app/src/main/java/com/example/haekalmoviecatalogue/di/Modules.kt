package com.example.haekalmoviecatalogue.di

import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.LocalDataSource
import com.example.haekalmoviecatalogue.data.source.local.room.MovieDao
import com.example.haekalmoviecatalogue.data.source.local.room.MovieDatabase
import com.example.haekalmoviecatalogue.data.source.remote.RemoteDataSource
import com.example.haekalmoviecatalogue.ui.detail.moviedetail.MovieDetailViewModel
import com.example.haekalmoviecatalogue.ui.detail.tvshowdetail.TvShowDetailViewModel
import com.example.haekalmoviecatalogue.ui.favorite.favoritemovie.FavoriteMovieViewModel
import com.example.haekalmoviecatalogue.ui.favorite.favoritetvshow.FavoriteTvShowViewModel
import com.example.haekalmoviecatalogue.ui.movie.MovieViewModel
import com.example.haekalmoviecatalogue.ui.tvshow.TvShowViewModel
import com.example.haekalmoviecatalogue.utils.AppExecutors
import com.example.haekalmoviecatalogue.utils.ConnectionLiveData
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { RemoteDataSource.getInstance() }
}

val movieDatabaseModule = module {
    single { MovieDatabase.getInstance(get()) }
}

val movieDaoModule = module {
    single { MovieDatabase.getInstance(get()).movieDao() }
}

val localDataSourceModule = module {
    single { LocalDataSource.getInstance(get()) }
}

val appExecutorsModule = module {
    single { AppExecutors() }
}

val movieRepository = module {
    single { MovieRepository.getInstance(get(), get(), get()) }
}

val connectionLiveData = module {
    single { ConnectionLiveData.getInstance(get()) }
}

val movieViewModelModule = module {
    viewModel { MovieViewModel(get(), get()) }
}

val tvShowViewModelModule = module {
    viewModel { TvShowViewModel(get(), get()) }
}

val movieDetailViewModelModule = module {
    viewModel { MovieDetailViewModel(get()) }
}

val tvShowDetailViewModelModule = module {
    viewModel { TvShowDetailViewModel(get()) }
}

val favoriteMovieViewModelModule = module {
    viewModel { FavoriteMovieViewModel(get()) }
}

val favoriteTvShowViewModelModule = module {
    viewModel { FavoriteTvShowViewModel(get()) }
}