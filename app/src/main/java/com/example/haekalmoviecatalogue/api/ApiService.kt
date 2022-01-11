package com.example.haekalmoviecatalogue.api

import com.example.haekalmoviecatalogue.data.source.remote.response.PopularMovieResponse
import com.example.haekalmoviecatalogue.data.source.remote.response.PopularTvShowResponse
import retrofit2.Call
import retrofit2.http.GET
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

}