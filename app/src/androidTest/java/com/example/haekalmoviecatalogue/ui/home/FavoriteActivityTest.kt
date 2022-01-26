package com.example.haekalmoviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.utils.DataDummy
import com.example.haekalmoviecatalogue.utils.EspressoIdlingResources
import org.junit.After
import org.junit.Before
import org.junit.Test

class FavoriteActivityTest {

    private val dummyPopularMovie = DataDummy.generateDummyPopularMovies(true)
    private val dummyPopularTvShow = DataDummy.generateDummyPopularTvShows(true)
    private val dummyMovieDetail = DataDummy.generateDummyMovieDetail()
    private val dummyTvShowDetail = DataDummy.generateDummyTvShowDetail()

    @Before
    fun setUp() {
        ActivityScenario.launch(FavoriteActivity::class.java)
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
        onView(withId(R.id.text_duration_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_release_date_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_score_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_status_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_type_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_network_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_score_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_status_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster_tvshow)).check(matches(isDisplayed()))
    }

}