package com.example.haekalmoviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieGenresEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.NetworkItemEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowGenresEntity
import com.example.haekalmoviecatalogue.utils.DataDummy
import com.example.haekalmoviecatalogue.utils.EspressoIdlingResources
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.floor

class HomeActivityTest {

    private val dummyPopularMovie = DataDummy.generateDummyPopularMovies(true)
    private val dummyPopularTvShow = DataDummy.generateDummyPopularTvShows(true)
    private val dummyMovieDetail = DataDummy.generateDummyMovieDetail()
    private val dummyTvShowDetail = DataDummy.generateDummyTvShowDetail()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyPopularMovie.results.size))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyPopularTvShow.results.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_movie)).check(matches(withText(dummyMovieDetail.title)))
        onView(withId(R.id.text_duration_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration_movie)).check(matches(withText(dummyMovieDetail.duration)))
        onView(withId(R.id.text_genre_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre_movie)).check(matches(withText(generateMovieGenres(dummyMovieDetail.genre))))
        onView(withId(R.id.text_overview_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_movie)).check(matches(withText(dummyMovieDetail.overview)))
        onView(withId(R.id.text_release_date_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_release_date_movie)).check(matches(withText(dummyMovieDetail.releaseDate)))
        onView(withId(R.id.rating_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_score_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_value_movie)).check(matches(withText(generateMovieDuration(dummyMovieDetail.duration))))
        onView(withId(R.id.text_status_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_status_movie)).check(matches(withText(dummyMovieDetail.status)))
        onView(withId(R.id.img_poster_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_tvshow)).check(matches(withText(dummyTvShowDetail.title)))
        onView(withId(R.id.text_type_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_type_tvshow)).check(matches(withText(dummyTvShowDetail.type)))
        onView(withId(R.id.text_genre_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre_tvshow)).check(matches(withText(generateTvShowGenres(dummyTvShowDetail.genre))))
        onView(withId(R.id.text_overview_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_tvshow)).check(matches(withText(dummyTvShowDetail.overview)))
        onView(withId(R.id.text_network_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_network_tvshow)).check(matches(withText(generateNetworks(dummyTvShowDetail.network))))
        onView(withId(R.id.rating_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_score_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_value_tvshow)).check(matches(withText(dummyTvShowDetail.userScore.toString())))
        onView(withId(R.id.text_status_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_status_tvshow)).check(matches(withText(dummyTvShowDetail.status)))
        onView(withId(R.id.img_poster_tvshow)).check(matches(isDisplayed()))
    }

    private fun generateMovieDuration(duration: Int?): String? {
        var durationParse: String? = null
        if (duration != null) {
            val hour: Double = floor(duration.toDouble() / 60)
            val minute: Double = duration.toDouble() % 60

            durationParse = "${hour.toInt()}h ${minute.toInt()}m"

        }
        return durationParse
    }

    private fun generateMovieGenres(genresItem: List<MovieGenresEntity>): String {
        val builder = StringBuilder()

        if (genresItem?.isEmpty()) {
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

    private fun generateTvShowGenres(genresItem: List<TvShowGenresEntity>): String {
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

    private fun generateNetworks(networksItem: List<NetworkItemEntity>): String {
        val builder = StringBuilder()

        networksItem.forEach { network  ->
            builder.append(network.name)
            if (network.name == networksItem.lastOrNull()?.name) {
                builder.append(".")
            } else {
                builder.append(", ")
            }
        }

        return builder.toString()
    }

}