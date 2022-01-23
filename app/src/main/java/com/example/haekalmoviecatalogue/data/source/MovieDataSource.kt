package com.example.haekalmoviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieDetailEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.PopularMovieEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.PopularTvShowEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowDetailEntity

interface MovieDataSource {

    fun getPopularMovies(): LiveData<PopularMovieEntity>

    fun getPopularTvShow(): LiveData<PopularTvShowEntity>

    fun getMovieDetail(movieId: Int?): LiveData<MovieDetailEntity>

    fun getTvShowDetail(tvShowId: Int?): LiveData<TvShowDetailEntity>

}