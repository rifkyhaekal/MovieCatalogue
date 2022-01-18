package com.example.haekalmoviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieDetailEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.PopularMovieEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.example.haekalmoviecatalogue.data.source.remote.response.MovieItem
import com.example.haekalmoviecatalogue.data.source.remote.response.TvShowItem

interface MovieDataSource  {
    fun getPopularMovie(): LiveData<PopularMovieEntity>

    fun getPopularTvShow(): LiveData<List<TvShowItem>>

    fun getMovieDetail(movieId: Int?): LiveData<MovieDetailEntity>

    fun getTvShowDetail(tvShowId: Int?): LiveData<TvShowDetailEntity>
}