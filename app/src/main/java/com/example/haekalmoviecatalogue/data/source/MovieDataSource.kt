package com.example.haekalmoviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieDetailEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieItemEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowItemEntity
import com.example.haekalmoviecatalogue.data.source.remote.response.TvShowItem

interface MovieDataSource  {
    fun getPopularMovie(): LiveData<List<MovieItemEntity>>

    fun getPopularTvShow(): LiveData<List<TvShowItemEntity>>

    fun getMovieDetail(movieId: Int?): LiveData<MovieDetailEntity>

    fun getTvShowDetail(tvShowId: Int?): LiveData<TvShowDetailEntity>
}