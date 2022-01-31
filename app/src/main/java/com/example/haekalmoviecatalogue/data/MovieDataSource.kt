package com.example.haekalmoviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.vo.Resource

interface MovieDataSource {

    fun getAllMovies(query: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun getAllTvShows(query: String): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

}