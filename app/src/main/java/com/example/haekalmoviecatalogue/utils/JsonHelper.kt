package com.example.haekalmoviecatalogue.utils

import com.example.haekalmoviecatalogue.api.ApiConfig
import com.example.haekalmoviecatalogue.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonHelper {

    fun getPopularMovies(): PopularMovieResponse {
        lateinit var list: PopularMovieResponse

        val client = ApiConfig.getApiService().getPopularMovie(Common.API_KEY, "en-US", 1)
        client.enqueue(object : Callback<List<MovieItem>> {
            override fun onResponse(
                call: Call<List<MovieItem>>,
                response: Response<List<MovieItem>>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        list = PopularMovieResponse(responseBody)
                    }
                }
            }

            override fun onFailure(call: Call<List<MovieItem>>, t: Throwable) {

            }

        })
        return list
    }

    fun getPopularTvShows(): List<TvShowItem> {
        val list = ArrayList<TvShowItem>()

        val client = ApiConfig.getApiService().getPopularTv(Common.API_KEY, "en-US", 1)
        client.enqueue(object : Callback<TvShowItem> {
            override fun onResponse(
                call: Call<TvShowItem>,
                response: Response<TvShowItem>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val tvShowResponse = TvShowItem(responseBody.id, responseBody.posterPath)
                        list.add(tvShowResponse)
                    }
                }
            }

            override fun onFailure(call: Call<TvShowItem>, t: Throwable) {

            }

        })
        return list
    }

    fun getMovieDetail(movieId: Int?): MovieDetailResponse {
        var movieDetailResponse: MovieDetailResponse? = null

        val client = ApiConfig.getApiService().getMovieDetail(movieId, Common.API_KEY)
        client.enqueue(object: Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val overview = responseBody.overview
                        val releaseDate = responseBody.releaseDate
                        val genres = responseBody.genres
                        val voteAverage = responseBody.voteAverage
                        val runtime = responseBody.runtime
                        val id = responseBody.id
                        val title = responseBody.title
                        val posterPath = responseBody.posterPath
                        val status = responseBody.posterPath

                        movieDetailResponse = MovieDetailResponse(overview, releaseDate, genres, voteAverage, runtime, id, title, posterPath, status)
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {

            }
        })

        return movieDetailResponse as MovieDetailResponse
    }

    fun getTvShowDetail(tvShowId: Int?): TvShowDetailResponse {
        var tvShowDetailResponse: TvShowDetailResponse? = null

        val client = ApiConfig.getApiService().getTvDetail(tvShowId, Common.API_KEY)
        client.enqueue(object: Callback<TvShowDetailResponse> {
            override fun onResponse(
                call: Call<TvShowDetailResponse>,
                response: Response<TvShowDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val overview = responseBody.overview
                        val genres = responseBody.genres
                        val voteAverage = responseBody.voteAverage
                        val name = responseBody.name
                        val id = responseBody.id
                        val networks = responseBody.networks
                        val type = responseBody.type
                        val posterPath = responseBody.posterPath
                        val status = responseBody.status

                        tvShowDetailResponse = TvShowDetailResponse(overview, genres, voteAverage, name, id, networks, type, posterPath, status)
                    }
                }
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {

            }
        })
        return tvShowDetailResponse as TvShowDetailResponse
    }

}