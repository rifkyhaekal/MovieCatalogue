package com.example.haekalmoviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.utils.DataDummy
import com.example.haekalmoviecatalogue.utils.EspressoIdlingResources
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private val dummyPopularMovie = DataDummy.generateDummyPopularMovies()
    private val dummyPopularTvShow = DataDummy.generateDummyPopularTvShows()

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
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyPopularMovie.size))
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.navigation_tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyPopularTvShow.size))
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
        onView(withId(R.id.navigation_tv_show)).perform(click())
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

    @Test
    fun loadMovieFavorite() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.rv_movie_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_release_date_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_score_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_status_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadTvShowFavorite() {
        onView(withId(R.id.navigation_tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(allOf(withText("TV SHOW"), isDescendantOfA(withId(R.id.tabs)))).perform(click())
        onView(withId(R.id.rv_tv_show_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_type_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_network_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_score_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_status_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }
}