package com.example.haekalmoviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haekalmoviecatalogue.api.ApiConfig
import com.example.haekalmoviecatalogue.data.source.remote.response.MovieDetailResponse
import com.example.haekalmoviecatalogue.data.source.remote.response.PopularMovieResponse
import com.example.haekalmoviecatalogue.data.source.remote.response.PopularTvShowResponse
import com.example.haekalmoviecatalogue.data.source.remote.response.TvShowDetailResponse
import com.example.haekalmoviecatalogue.utils.Common
import com.example.haekalmoviecatalogue.utils.EspressoIdlingResources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    fun getPopularMovies(): LiveData<ApiResponse<PopularMovieResponse>> {
        EspressoIdlingResources.increment()
        val popularMovieResults = MutableLiveData<ApiResponse<PopularMovieResponse>>()
        val client = ApiConfig.getApiService().getPopularMovie(Common.API_KEY, "en-US", 1)
        client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        popularMovieResults.value = ApiResponse.success(responseBody)
                        EspressoIdlingResources.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Log.e("PopularMovie", "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }

        })

        return popularMovieResults
    }

    fun getPopularTvShows(): LiveData<ApiResponse<PopularTvShowResponse>> {
        EspressoIdlingResources.increment()
        val popularTvShowResults = MutableLiveData<ApiResponse<PopularTvShowResponse>>()
        val client = ApiConfig.getApiService().getPopularTv(Common.API_KEY, "en-US", 1)
        client.enqueue(object : Callback<PopularTvShowResponse> {
            override fun onResponse(
                call: Call<PopularTvShowResponse>,
                response: Response<PopularTvShowResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        popularTvShowResults.value = ApiResponse.success(responseBody)
                        EspressoIdlingResources.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<PopularTvShowResponse>, t: Throwable) {
                Log.e("PopularTvShow", "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }

        })

        return popularTvShowResults
    }

    fun getMovieDetail(movieId: Int?): LiveData<ApiResponse<MovieDetailResponse>> {
        EspressoIdlingResources.increment()
        val movieDetailResult = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        val client = ApiConfig.getApiService().getMovieDetail(movieId, Common.API_KEY)
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        movieDetailResult.value = ApiResponse.success(responseBody)
                        EspressoIdlingResources.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e("DetailMovie", "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })

        return movieDetailResult
    }

    fun getTvShowDetail(tvShowId: Int?): LiveData<ApiResponse<TvShowDetailResponse>> {
        EspressoIdlingResources.increment()
        val tvShowDetailResult = MutableLiveData<ApiResponse<TvShowDetailResponse>>()
        val client = ApiConfig.getApiService().getTvDetail(tvShowId, Common.API_KEY)
        client.enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(
                call: Call<TvShowDetailResponse>,
                response: Response<TvShowDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        tvShowDetailResult.value = ApiResponse.success(responseBody)
                        EspressoIdlingResources.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                Log.e("DetailMovie", "onFailure: ${t.message.toString()}")
                EspressoIdlingResources.decrement()
            }
        })

        return tvShowDetailResult
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