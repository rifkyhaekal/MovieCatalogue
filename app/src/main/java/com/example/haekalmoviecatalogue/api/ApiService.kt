package com.example.haekalmoviecatalogue.api

import com.example.haekalmoviecatalogue.data.source.remote.response.*
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
    ): Call<List<MovieItem>>

    @GET("tv/popular")
    fun getPopularTv(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TvShowItem>

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