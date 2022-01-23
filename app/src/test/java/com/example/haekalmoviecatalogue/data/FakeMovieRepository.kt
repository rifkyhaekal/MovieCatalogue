package com.example.haekalmoviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haekalmoviecatalogue.data.source.MovieDataSource
import com.example.haekalmoviecatalogue.data.source.local.entity.*
import com.example.haekalmoviecatalogue.data.source.remote.RemoteDataSource
import com.example.haekalmoviecatalogue.data.source.remote.response.*
import kotlin.math.floor

class FakeMovieRepository (private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    override fun getPopularMovies(): LiveData<PopularMovieEntity> {
        val popularMovieResults = MutableLiveData<PopularMovieEntity>()
        remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadPopularMoviesCallback {
            override fun onAllPopularMoviesReceived(
                success: Boolean,
                popularMovieResponses: List<MovieItem>?
            ) {
                val popularMovieList = ArrayList<MovieItemEntity>()
                if (popularMovieResponses != null) {
                    for (response in popularMovieResponses) {
                        val movie = MovieItemEntity(
                            id = response.id,
                            posterPath = response.posterPath
                        )
                        popularMovieList.add(movie)
                    }
                }
                val popularMovie = PopularMovieEntity(success, popularMovieList)
                popularMovieResults.postValue(popularMovie)
            }
        })
        return popularMovieResults
    }

    override fun getPopularTvShow(): LiveData<PopularTvShowEntity> {
        val popularTvShowResults = MutableLiveData<PopularTvShowEntity>()
        remoteDataSource.getPopularTvShows(object : RemoteDataSource.LoadPopularTvShowCallback {
            override fun onAllPopularTvShowsReceived(
                success: Boolean,
                popularTvShowResponses: List<TvShowItem>?
            ) {
                val popularTvShowList = ArrayList<TvShowItemEntity>()
                if (popularTvShowResponses != null) {
                    for (response in popularTvShowResponses) {
                        val tvShow = TvShowItemEntity(
                            id = response.id,
                            posterPath = response.posterPath
                        )
                        popularTvShowList.add(tvShow)
                    }
                }
                val popularTvShow = PopularTvShowEntity(success, popularTvShowList)
                popularTvShowResults.postValue(popularTvShow)
            }
        })
        return popularTvShowResults
    }

    override fun getMovieDetail(movieId: Int?): LiveData<MovieDetailEntity> {
        val movieDetailResults = MutableLiveData<MovieDetailEntity>()

        remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.LoadMovieDetailCallback{
            override fun onMovieDetailReceived(movieDetailResponse: MovieDetailResponse) {
                val detailMovie = MovieDetailEntity(
                    movieDetailResponse.id,
                    movieDetailResponse.title,
                    generateMovieGenres(movieDetailResponse.genres),
                    movieDetailResponse.overview,
                    generateMovieDuration(movieDetailResponse.runtime),
                    movieDetailResponse.voteAverage,
                    movieDetailResponse.releaseDate,
                    movieDetailResponse.status,
                    movieDetailResponse.posterPath
                )
                movieDetailResults.postValue(detailMovie)
            }
        })
        return movieDetailResults
    }

    override fun getTvShowDetail(tvShowId: Int?): LiveData<TvShowDetailEntity> {
        val tvShowDetailResults = MutableLiveData<TvShowDetailEntity>()

        remoteDataSource.getTvShowDetail(tvShowId, object : RemoteDataSource.LoadTvShowDetailCallback {
            override fun onTvShowDetailReceived(tvShowDetailResponse: TvShowDetailResponse) {
                val tvShow = TvShowDetailEntity(
                    tvShowDetailResponse.id,
                    tvShowDetailResponse.name,
                    tvShowDetailResponse.overview,
                    tvShowDetailResponse.status,
                    tvShowDetailResponse.type,
                    generateTvShowGenres(tvShowDetailResponse.genres),
                    generateNetworks(tvShowDetailResponse.networks),
                    tvShowDetailResponse.voteAverage,
                    tvShowDetailResponse.posterPath
                )
                tvShowDetailResults.postValue(tvShow)
            }
        })
        return tvShowDetailResults
    }

    private fun generateMovieGenres(genresItem: List<MovieGenresItem>): String {
        val builder = StringBuilder()

        if (genresItem.isEmpty()) {
            builder.append("-")
        } else {
            genresItem.forEach { genre ->
                builder.append(genre.name)
                if (genre.name == genresItem.lastOrNull()?.name) {
                    builder.append(".")
                } else {
                    builder.append(", ")
                }
            }
        }

        return builder.toString()
    }

    private fun generateMovieDuration(duration: Int?): String {
        lateinit var durationParse: String
        if (duration != null) {
            val hour: Double = floor(duration.toDouble() / 60)
            val minute: Double = duration.toDouble() % 60

            durationParse = "${hour.toInt()}h ${minute.toInt()}m"

        }
        return durationParse
    }

    private fun generateNetworks(networksItem: List<NetworksItem>): String {
        val builder = StringBuilder()

        networksItem.forEach { network ->
            builder.append(network.name)
            if (network.name == networksItem.lastOrNull()?.name) {
                builder.append(".")
            } else {
                builder.append(", ")
            }
        }

        return builder.toString()
    }

    private fun generateTvShowGenres(genresItem: List<TvGenresItem>): String {
        val builder = StringBuilder()

        if (genresItem.isEmpty()) {
            builder.append("-")
        } else {
            genresItem.forEach { genre ->
                builder.append(genre.name)
                if (genre.name == genresItem.lastOrNull()?.name) {
                    builder.append(".")
                } else {
                    builder.append(", ")
                }
            }
        }

        return builder.toString()
    }

}