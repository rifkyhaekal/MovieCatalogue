package com.example.haekalmoviecatalogue.ui.movie

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.api.ApiConfig
import com.example.haekalmoviecatalogue.data.source.local.entity.ErrorEntity
import com.example.haekalmoviecatalogue.data.source.remote.response.MovieItem
import com.example.haekalmoviecatalogue.data.source.remote.response.PopularMovieResponse
import com.example.haekalmoviecatalogue.utils.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(application: Application) : ViewModel() {
    private val app = application

    private val _popularMovie = MutableLiveData<List<MovieItem>>()
    val popularMovie: LiveData<List<MovieItem>> = _popularMovie

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showError = MutableLiveData(ErrorEntity(false, null, null))
    val showError: LiveData<ErrorEntity> get() = _showError

    init {
        getPopularMovie()
    }

    fun getPopularMovie() {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getPopularMovie(Common.API_KEY, "en-US", 1)
        client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _popularMovie.value = responseBody.results
                    }
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                _isLoading.value = false
                _showError.value = _showError.value?.copy(true, app.resources.getString(R.string.no_connection), R.drawable.no_connection)
            }

        })
    }
}