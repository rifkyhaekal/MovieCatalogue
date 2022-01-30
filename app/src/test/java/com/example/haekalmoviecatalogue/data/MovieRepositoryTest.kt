package com.example.haekalmoviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.haekalmoviecatalogue.data.source.local.LocalDataSource
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.data.source.remote.RemoteDataSource
import com.example.haekalmoviecatalogue.data.source.remote.response.MovieGenresItem
import com.example.haekalmoviecatalogue.data.source.remote.response.NetworksItem
import com.example.haekalmoviecatalogue.data.source.remote.response.TvGenresItem
import com.example.haekalmoviecatalogue.utils.AppExecutors
import com.example.haekalmoviecatalogue.utils.DataDummy
import com.example.haekalmoviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import kotlin.math.floor

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val popularMovieResponse = DataDummy.generateRemoteDummyPopularMovies()
    private val popularTvShowResponse = DataDummy.generateRemoteDummyPopularTvShows()
    private val movieId = popularMovieResponse[0].id
    private val tvShowId = popularTvShowResponse[0].id
    private val movieDetailResponse = DataDummy.generateRemoteDummyMovieDetail()
    private val tvShowDetailResponse = DataDummy.generateRemoteDummyTvShowDetail()

    @Test
    fun getPopularMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummy.generateDummyPopularMovies()
        `when`(local.getAllMovies()).thenReturn(dummyMovies)

        val popularMovieEntities = LiveDataTestUtil.getValue(movieRepository.getAllMovies())
        verify(local).getAllMovies()
        assertNotNull(popularMovieEntities)
        assertEquals(popularMovieResponse.size.toLong(), popularMovieEntities.data?.size?.toLong())
    }

    @Test
    fun getPopularTvShow() {
        val dummyTvShows = MutableLiveData<List<TvShowEntity>>()
        dummyTvShows.value = DataDummy.generateDummyPopularTvShows()
        `when`(local.getAllTvShows()).thenReturn(dummyTvShows)

        val popularTvShowEntities = LiveDataTestUtil.getValue(movieRepository.getAllTvShows())
        verify(local).getAllTvShows()
        assertNotNull(popularTvShowEntities)
        assertEquals(popularMovieResponse.size.toLong(), popularTvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = DataDummy.generateDummyMovieWithDetail(true)
        `when`(local.getMovieDetail(movieId)).thenReturn(dummyEntity)

        val resultMovie = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))
        verify(local).getMovieDetail(movieId)
        assertNotNull(resultMovie)
        assertNotNull(resultMovie.data?.movieDetailEntity)
        assertEquals(generateMovieGenres(movieDetailResponse.genres), resultMovie.data?.movieDetailEntity?.genre)
        assertEquals(movieDetailResponse.id, resultMovie.data?.movieId)
        assertEquals(movieDetailResponse.overview, resultMovie.data?.movieDetailEntity?.overview)
        assertEquals(movieDetailResponse.posterPath, resultMovie.data?.imgPoster)
        assertEquals(movieDetailResponse.releaseDate, resultMovie.data?.movieDetailEntity?.releaseDate)
        assertEquals(movieDetailResponse.status, resultMovie.data?.movieDetailEntity?.status)
        assertEquals(movieDetailResponse.title, resultMovie.data?.movieDetailEntity?.title)
        assertEquals(movieDetailResponse.voteAverage.toFloat(), resultMovie.data?.movieDetailEntity?.userScore)
        assertEquals(generateMovieDuration(movieDetailResponse.runtime), resultMovie.data?.movieDetailEntity?.duration)
    }

    @Test
    fun getTvShowDetail() {
        val dummyEntity = MutableLiveData<TvShowEntity>()
        dummyEntity.value = DataDummy.generateDummyTvShowWithDetail(true)
        `when`(local.getTvShowDetail(tvShowId)).thenReturn(dummyEntity)

        val resultTvShow = LiveDataTestUtil.getValue(movieRepository.getTvShowDetail(tvShowId))
        verify(local).getTvShowDetail(tvShowId)
        assertNotNull(resultTvShow)
        assertNotNull(resultTvShow.data?.tvShowDetailEntity)
        assertEquals(generateTvShowGenres(tvShowDetailResponse.genres), resultTvShow.data?.tvShowDetailEntity?.genre)
        assertEquals(generateNetworks(tvShowDetailResponse.networks), resultTvShow.data?.tvShowDetailEntity?.network)
        assertEquals(tvShowDetailResponse.posterPath, resultTvShow.data?.imgPoster)
        assertEquals(tvShowDetailResponse.id, resultTvShow.data?.tvShowId)
        assertEquals(tvShowDetailResponse.name, resultTvShow.data?.tvShowDetailEntity?.title)
        assertEquals(tvShowDetailResponse.overview, resultTvShow.data?.tvShowDetailEntity?.overview)
        assertEquals(tvShowDetailResponse.status, resultTvShow.data?.tvShowDetailEntity?.status)
        assertEquals(tvShowDetailResponse.type, resultTvShow.data?.tvShowDetailEntity?.type)
        assertEquals(tvShowDetailResponse.voteAverage.toFloat(), resultTvShow.data?.tvShowDetailEntity?.userScore)
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