package com.example.haekalmoviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_movie)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.text_duration_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration_movie)).check(matches(withText(dummyMovie[0].duration)))
        onView(withId(R.id.text_genre_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre_movie)).check(matches(withText(dummyMovie[0].genre)))
        onView(withId(R.id.text_overview_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_movie)).check(matches(withText(dummyMovie[0].overview)))
        onView(withId(R.id.text_release_date_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_release_date_movie)).check(matches(withText(dummyMovie[0].releaseDate)))
        onView(withId(R.id.text_score_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_score_movie)).check(matches(withText(dummyMovie[0].userScore)))
        onView(withId(R.id.text_status_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_status_movie)).check(matches(withText(dummyMovie[0].status)))
        onView(withId(R.id.img_poster_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_tvshow)).check(matches(withText(dummyTvShow[0].title)))
        onView(withId(R.id.text_type_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_type_tvshow)).check(matches(withText(dummyTvShow[0].type)))
        onView(withId(R.id.text_genre_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre_tvshow)).check(matches(withText(dummyTvShow[0].genre)))
        onView(withId(R.id.text_overview_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview_tvshow)).check(matches(withText(dummyTvShow[0].overview)))
        onView(withId(R.id.text_network_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_network_tvshow)).check(matches(withText(dummyTvShow[0].network)))
        onView(withId(R.id.text_score_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_score_tvshow)).check(matches(withText(dummyTvShow[0].userScore)))
        onView(withId(R.id.text_status_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.text_status_tvshow)).check(matches(withText(dummyTvShow[0].status)))
        onView(withId(R.id.img_poster_tvshow)).check(matches(isDisplayed()))
    }











}