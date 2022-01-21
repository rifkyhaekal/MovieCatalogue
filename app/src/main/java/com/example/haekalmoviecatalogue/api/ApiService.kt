package com.example.haekalmoviecatalogue.api

import com.example.haekalmoviecatalogue.data.source.remote.response.MovieDetailResponse
import com.example.haekalmoviecatalogue.data.source.remote.response.PopularMovieResponse
import com.example.haekalmoviecatalogue.data.source.remote.response.PopularTvShowResponse
import com.example.haekalmoviecatalogue.data.source.remote.response.TvShowDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<PopularMovieResponse>

    @GET("tv/popular")
    fun getPopularTv(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<PopularTvShowResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movie_id: Int?,
        @Query("api_key") api_key: String
    ): Call<MovieDetailResponse>

    @GET("tv/{tv_id}")
    fun getTvDetail(
        @Path("tv_id") tv_id: Int?,
        @Query("api_key") api_key: String
    ): Call<TvShowDetailResponse>

}