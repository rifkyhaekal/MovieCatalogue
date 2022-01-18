package com.example.haekalmoviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haekalmoviecatalogue.data.source.local.entity.*
import com.example.haekalmoviecatalogue.data.source.remote.RemoteDataSource
import com.example.haekalmoviecatalogue.data.source.remote.response.*

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieDataSource{

    override fun getPopularMovie(): LiveData<List<MovieItemEntity>> {
        val popularMovieResults = MutableLiveData<List<MovieItemEntity>>()
        remoteDataSource.getAllPopularMovies(object : RemoteDataSource.LoadPopularMoviesCallback {
            override fun onAllPopularMovieReceived(popularMovieResponses: List<MovieItem>) {
                val movieList = ArrayList<MovieItemEntity>()
                for (response in popularMovieResponses) {
                    val movie = MovieItemEntity(
                        response.id,
                        response.posterPath
                    )
                    movieList.add(movie)
                }
                popularMovieResults.postValue(movieList)
            }
        })
        return popularMovieResults
    }

    override fun getPopularTvShow(): LiveData<List<TvShowItemEntity>> {
        val popularTvShowResults = MutableLiveData<List<TvShowItemEntity>>()
        remoteDataSource.getAllPopularTvShows(object : RemoteDataSource.LoadPopularTvShowCallback {
            override fun onAllPopularTvShowReceived(popularTvShowResponses: List<TvShowItem>) {
                val popularTvShowList = ArrayList<TvShowItemEntity>()
                for (response in popularTvShowResponses) {
                    val tvShow = TvShowItemEntity(
                        response.id,
                        response.posterPath
                    )
                    popularTvShowList.add(tvShow)
                }
                popularTvShowResults.postValue(popularTvShowList)
            }
        })
        return popularTvShowResults
    }

    override fun getMovieDetail(movieId: Int?): LiveData<MovieDetailEntity> {
        val movieDetailResults = MutableLiveData<MovieDetailEntity>()

        remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.LoadMovieDetailCallback{
            override fun onMovieDetailReceived(movieDetailResponse: MovieDetailResponse) {
                val movie = MovieDetailEntity(
                    movieDetailResponse.id,
                    movieDetailResponse.title,
                    movieDetailResponse.genres,
                    movieDetailResponse.overview,
                    movieDetailResponse.runtime,
                    movieDetailResponse.voteAverage,
                    movieDetailResponse.releaseDate,
                    movieDetailResponse.status,
                    movieDetailResponse.posterPath
                )
                movieDetailResults.postValue(movie)
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
                    tvShowDetailResponse.genres,
                    tvShowDetailResponse.networks,
                    tvShowDetailResponse.voteAverage,
                    tvShowDetailResponse.posterPath
                )
                tvShowDetailResults.postValue(tvShow)
            }
        })
        return tvShowDetailResults
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData).apply { instance = this }
            }
    }

}