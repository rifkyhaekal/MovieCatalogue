package com.example.haekalmoviecatalogue.data.source.remote

import com.example.haekalmoviecatalogue.data.source.remote.response.*
import com.example.haekalmoviecatalogue.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){

    fun getAllPopularMovies (callback: LoadPopularMoviesCallback) {
        callback.onAllPopularMovieReceived(jsonHelper.getPopularMovies())
    }

    fun getAllPopularTvShows (callback: LoadPopularTvShowCallback) {
        callback.onAllPopularTvShowReceived(jsonHelper.getPopularTvShows())
    }

    fun getMovieDetail (movieId: Int?, callback: LoadMovieDetailCallback) {
        callback.onMovieDetailReceived(jsonHelper.getMovieDetail(movieId))
    }

    fun getTvShowDetail (tvShowId: Int?, callback: LoadTvShowDetailCallback) {
        callback.onTvShowDetailReceived(jsonHelper.getTvShowDetail(tvShowId))
    }

    interface  LoadPopularMoviesCallback {
        fun onAllPopularMovieReceived(popularMovieResponses: PopularMovieResponse)
    }

    interface LoadPopularTvShowCallback {
        fun onAllPopularTvShowReceived(popularTvShowResponses: List<TvShowItem>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived (movieDetailResponse: MovieDetailResponse)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived (tvShowDetailResponse: TvShowDetailResponse)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }
}