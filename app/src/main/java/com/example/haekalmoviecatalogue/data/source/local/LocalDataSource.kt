package com.example.haekalmoviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    fun getAllMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity> = mMovieDao.getMovies(query)

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getFavoriteMovies()

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity> = mMovieDao.getMovieDetail(movieId)

    fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        mMovieDao.updateMovie(movie)
    }

    fun updateMovieDetail(movieId: Int, title: String, genre: String, overview: String, duration: String, userScore: Float, releaseDate: String, status: String) {
        mMovieDao.updateMovieDetail(movieId, title, genre, overview, duration, userScore, releaseDate, status)
    }

    fun getAllTvShows(query: SupportSQLiteQuery): DataSource.Factory<Int, TvShowEntity> = mMovieDao.getTvShows(query)

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> = mMovieDao.getFavoriteTvShows()

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> = mMovieDao.getTvShowDetail(tvShowId)

    fun insertTvShow(tvShows: List<TvShowEntity>) = mMovieDao.insertTvShows(tvShows)

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorite = newState
        mMovieDao.updateTvShow(tvShow)
    }

    fun updateTvShowDetail(tvShowId: Int, title: String, overview: String, status: String, type: String, genre: String, network: String, userScore: Float) {
        mMovieDao.updateTvShowDetail(tvShowId, title, overview, status, type, genre, network, userScore)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }
}