package com.example.haekalmoviecatalogue.ui.tvshow

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.api.ApiConfig
import com.example.haekalmoviecatalogue.data.source.local.entity.ErrorEntity
import com.example.haekalmoviecatalogue.data.source.remote.response.PopularTvShowResponse
import com.example.haekalmoviecatalogue.data.source.remote.response.TvShowItem
import com.example.haekalmoviecatalogue.utils.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowViewModel(application: Application) : ViewModel() {
    private val app = application

    private val _popularTvShow = MutableLiveData<List<TvShowItem>>()
    val popularTvShow: LiveData<List<TvShowItem>> = _popularTvShow

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showError = MutableLiveData(ErrorEntity(false, null, null))
    val showError: LiveData<ErrorEntity> get() = _showError

    init {
        getPopularTvShow()
    }

    fun getPopularTvShow() {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getPopularTv(Common.API_KEY, "en-US", 1)
        client.enqueue(object : Callback<PopularTvShowResponse> {
            override fun onResponse(
                call: Call<PopularTvShowResponse>,
                response: Response<PopularTvShowResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _popularTvShow.value = responseBody.results
                    }
                }
            }

            override fun onFailure(call: Call<PopularTvShowResponse>, t: Throwable) {
                _isLoading.value = false
                _showError.value = _showError.value?.copy(true, app.resources.getString(R.string.no_connection), R.drawable.no_connection)
            }

        })
    }
}