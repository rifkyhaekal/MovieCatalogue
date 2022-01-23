package com.example.haekalmoviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.haekalmoviecatalogue.data.source.remote.RemoteDataSource
import com.example.haekalmoviecatalogue.data.source.remote.response.MovieGenresItem
import com.example.haekalmoviecatalogue.data.source.remote.response.NetworksItem
import com.example.haekalmoviecatalogue.data.source.remote.response.TvGenresItem
import com.example.haekalmoviecatalogue.utils.DataDummy
import com.example.haekalmoviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import kotlin.math.floor

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val popularMovieResponse = DataDummy.generateRemoteDummyPopularMovies()
    private val popularTvShowResponse = DataDummy.generateRemoteDummyPopularTvShows()
    private val movieId = popularMovieResponse[0].id
    private val tvShowId = popularTvShowResponse[0].id
    private val movieDetailResponse = DataDummy.generateRemoteDummyMovieDetail()
    private val tvShowDetailResponse = DataDummy.generateRemoteDummyTvShowDetail()
    private val DELTA = 1e-15

    @Test
    fun getPopularMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadPopularMoviesCallback)
                .onAllPopularMoviesReceived(true, popularMovieResponse)
            null
        }.`when`(remote).getPopularMovies(any())
        val popularMovieEntities = LiveDataTestUtil.getValue(movieRepository.getPopularMovies())
        verify(remote).getPopularMovies(any())
        assertNotNull(popularMovieEntities)
        assertEquals(popularMovieResponse.size.toLong(), popularMovieEntities.results.size.toLong())
    }

    @Test
    fun getPopularTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadPopularTvShowCallback)
                .onAllPopularTvShowsReceived(true, popularTvShowResponse)
            null
        }.`when`(remote).getPopularTvShows(any())
        val popularTvShowEntities = LiveDataTestUtil.getValue(movieRepository.getPopularTvShow())
        verify(remote).getPopularTvShows(any())
        assertNotNull(popularTvShowEntities)
        assertEquals(popularMovieResponse.size.toLong(), popularTvShowEntities.results.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
                .onMovieDetailReceived(movieDetailResponse)
            null
        }.`when`(remote).getMovieDetail(eq(movieId), any())
        val resultMovie = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))
        verify(remote).getMovieDetail(eq(movieId), any())
        assertNotNull(resultMovie)
        assertEquals(movieDetailResponse.posterPath, resultMovie.imgPoster)
        assertEquals(generateMovieGenres(movieDetailResponse.genres), resultMovie.genre)
        assertEquals(movieDetailResponse.id, resultMovie.movieId)
        assertEquals(movieDetailResponse.overview, resultMovie.overview)
        assertEquals(movieDetailResponse.posterPath, resultMovie.imgPoster)
        assertEquals(movieDetailResponse.releaseDate, resultMovie.releaseDate)
        assertEquals(movieDetailResponse.status, resultMovie.status)
        assertEquals(movieDetailResponse.title, resultMovie.title)
        assertEquals(movieDetailResponse.voteAverage, resultMovie.userScore, DELTA)
        assertEquals(generateMovieDuration(movieDetailResponse.runtime), resultMovie.duration)
    }

    @Test
    fun getTvShowDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback)
                .onTvShowDetailReceived(tvShowDetailResponse)
            null
        }.`when`(remote).getTvShowDetail(eq(tvShowId), any())
        val resultTvShow = LiveDataTestUtil.getValue(movieRepository.getTvShowDetail(tvShowId))
        verify(remote).getTvShowDetail(eq(tvShowId), any())
        assertNotNull(resultTvShow)
        assertEquals(generateTvShowGenres(tvShowDetailResponse.genres), resultTvShow.genre)
        assertEquals(generateNetworks(tvShowDetailResponse.networks), resultTvShow.network)
        assertEquals(tvShowDetailResponse.posterPath, resultTvShow.imgPoster)
        assertEquals(tvShowDetailResponse.id, resultTvShow.tvShowId)
        assertEquals(tvShowDetailResponse.name, resultTvShow.title)
        assertEquals(tvShowDetailResponse.overview, resultTvShow.overview)
        assertEquals(tvShowDetailResponse.status, resultTvShow.status)
        assertEquals(tvShowDetailResponse.type, resultTvShow.type)
        assertEquals(tvShowDetailResponse.voteAverage, resultTvShow.userScore, DELTA)
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