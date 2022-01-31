package com.example.haekalmoviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.haekalmoviecatalogue.data.source.local.LocalDataSource
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.data.source.remote.ApiResponse
import com.example.haekalmoviecatalogue.data.source.remote.RemoteDataSource
import com.example.haekalmoviecatalogue.data.source.remote.response.*
import com.example.haekalmoviecatalogue.utils.AppExecutors
import com.example.haekalmoviecatalogue.vo.Resource
import kotlin.math.floor

class MovieRepository private constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors)
    : MovieDataSource {

    override fun getAllMovies(query: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, PopularMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllMovies(query), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<PopularMovieResponse>> =
                remoteDataSource.getPopularMovies()

            override fun saveCallResult(data: PopularMovieResponse) {
                val popularMovieList = ArrayList<MovieEntity>()
                for (response in data.results) {
                    val movie = MovieEntity(
                        movieId = response.id,
                        imgPoster = response.posterPath
                    )
                    popularMovieList.add(movie)
                }

                localDataSource.insertMovies(popularMovieList)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieDetail(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data?.movieDetailEntity == null

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovieDetail(movieId)

            override fun saveCallResult(data: MovieDetailResponse) =
                localDataSource.updateMovieDetail(
                    data.id,
                    data.title,
                    generateMovieGenres(data.genres),
                    data.overview,
                    generateMovieDuration(data.runtime),
                    data.voteAverage.toFloat(),
                    data.releaseDate,
                    data.status)

        }.asLiveData()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, state) }

    override fun getAllTvShows(query: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, PopularTvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllTvShows(query), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<PopularTvShowResponse>> =
                remoteDataSource.getPopularTvShows()

            override fun saveCallResult(data: PopularTvShowResponse) {
                val popularTvShowResults = ArrayList<TvShowEntity>()
                for (response in data.results) {
                    val tvShow = TvShowEntity(
                        tvShowId = response.id,
                        imgPoster = response.posterPath
                    )
                    popularTvShowResults.add(tvShow)
                }

                localDataSource.insertTvShow(popularTvShowResults)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowDetail(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data?.tvShowDetailEntity == null

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getTvShowDetail(tvShowId)

            override fun saveCallResult(data: TvShowDetailResponse) {
                localDataSource.updateTvShowDetail(
                    data.id,
                    data.name,
                    data.overview,
                    data.status,
                    data.type,
                    generateTvShowGenres(data.genres),
                    generateNetworks(data.networks),
                    data.voteAverage.toFloat()
                )
            }
        }.asLiveData()
    }

    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow, state) }

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

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localDataSource, appExecutors).apply { instance = this }
            }
    }

}