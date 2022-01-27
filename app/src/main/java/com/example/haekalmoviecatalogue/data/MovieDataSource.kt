package com.example.haekalmoviecatalogue.data

import androidx.lifecycle.LiveData
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.vo.Resource

interface MovieDataSource {

    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getFavoriteMovies(): LiveData<List<MovieEntity>>

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun getAllTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>>

    fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

}