package com.example.haekalmoviecatalogue.data.source.remote

import android.content.Context
import android.util.Log
import com.example.haekalmoviecatalogue.api.ApiConfig
import com.example.haekalmoviecatalogue.data.source.remote.response.*
import com.example.haekalmoviecatalogue.utils.Common
import com.example.haekalmoviecatalogue.utils.EspressoIdlingResources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val context: Context) {
    fun getPopularMovies(callback: LoadPopularMoviesCallback) {
        EspressoIdlingResources.increment()
        val client = ApiConfig.getApiService().getPopularMovie(Common.API_KEY, "en-US", 1)
        client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onAllPopularMoviesReceived(true, responseBody.results)
                        EspressoIdlingResources.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                callback.onAllPopularMoviesReceived(false, null)
                EspressoIdlingResources.decrement()
            }

        })
    }

    fun getPopularTvShows(callback: LoadPopularTvShowCallback) {
        EspressoIdlingResources.increment()
        val client = ApiConfig.getApiService().getPopularTv(Common.API_KEY, "en-US", 1)
        client.enqueue(object : Callback<PopularTvShowResponse> {
            override fun onResponse(
                call: Call<PopularTvShowResponse>,
                response: Response<PopularTvShowResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onAllPopularTvShowsReceived(true, responseBody.results)
                        EspressoIdlingResources.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<PopularTvShowResponse>, t: Throwable) {
                callback.onAllPopularTvShowsReceived(false, null)
                EspressoIdlingResources.decrement()
            }

        })
    }

    fun getMovieDetail(movieId: Int?, callback: LoadMovieDetailCallback) {
        EspressoIdlingResources.increment()
        val client = ApiConfig.getApiService().getMovieDetail(movieId, Common.API_KEY)
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onMovieDetailReceived(responseBody)
                        EspressoIdlingResources.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e("DetailMovie", "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })
    }

    fun getTvShowDetail(tvShowId: Int?, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResources.increment()
        val client = ApiConfig.getApiService().getTvDetail(tvShowId, Common.API_KEY)
        client.enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(
                call: Call<TvShowDetailResponse>,
                response: Response<TvShowDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onTvShowDetailReceived(responseBody)
                        EspressoIdlingResources.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                Log.e("DetailMovie", "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })
    }

    interface LoadPopularMoviesCallback {
        fun onAllPopularMoviesReceived(success: Boolean, popularMovieResponses: List<MovieItem>?)
    }

    interface LoadPopularTvShowCallback {
        fun onAllPopularTvShowsReceived(success: Boolean, popularTvShowResponses: List<TvShowItem>?)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieDetailResponse: MovieDetailResponse)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowDetailResponse: TvShowDetailResponse)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(context: Context): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(context).apply { instance = this }
            }
    }
}