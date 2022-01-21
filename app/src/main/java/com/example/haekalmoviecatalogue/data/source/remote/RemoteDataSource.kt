package com.example.haekalmoviecatalogue.data.source.remote

import android.util.Log
import com.example.haekalmoviecatalogue.api.ApiConfig
import com.example.haekalmoviecatalogue.data.source.remote.response.*
import com.example.haekalmoviecatalogue.utils.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    fun getPopularMovies(callback: LoadPopularMoviesCallback) {
        val client = ApiConfig.getApiService().getPopularMovie(Common.API_KEY, "en-US", 1)
            client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onAllPopularMoviesReceived(responseBody.results)
                    }
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Log.e("Error: ", "${t.message.toString()}")
            }

        })
    }

    fun getPopularTvShows(callback: LoadPopularTvShowCallback) {
        val client = ApiConfig.getApiService().getPopularTv(Common.API_KEY, "en-US", 1)
        client.enqueue(object : Callback<PopularTvShowResponse> {
            override fun onResponse(
                call: Call<PopularTvShowResponse>,
                response: Response<PopularTvShowResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onAllPopularTvShowsReceived(responseBody.results)
                    }
                }
            }

            override fun onFailure(call: Call<PopularTvShowResponse>, t: Throwable) {
                Log.e("Error: ", "${t.message.toString()}")
            }

        })
    }

    fun getMovieDetail(movieId: Int?, callback: LoadMovieDetailCallback) {
        val client = ApiConfig.getApiService().getMovieDetail(movieId, Common.API_KEY)
        client.enqueue(object: Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onMovieDetailReceived(responseBody)
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {

            }
        })
    }

    fun getTvShowDetail(tvShowId: Int?, callback: LoadTvShowDetailCallback) {
        val client = ApiConfig.getApiService().getTvDetail(tvShowId, Common.API_KEY)
        client.enqueue(object: Callback<TvShowDetailResponse> {
            override fun onResponse(
                call: Call<TvShowDetailResponse>,
                response: Response<TvShowDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onTvShowDetailReceived(responseBody)
                    }
                }
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {

            }
        })
    }

    interface LoadPopularMoviesCallback {
        fun onAllPopularMoviesReceived(popularMovieResponses: List<MovieItem>)
    }

    interface LoadPopularTvShowCallback {
        fun onAllPopularTvShowsReceived(popularTvShowResponses: List<TvShowItem>)
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

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }
}