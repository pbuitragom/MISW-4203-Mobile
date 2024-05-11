package co.com.uniandes.vinilos.performer.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import co.com.uniandes.vinilos.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class PerformerDetailActivityTest   {

    @get: Rule
    val activityRule = ActivityScenarioRule(PerformerActivity::class.java)

    @Test
    fun test_visibility_performerTitle() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_performerList() {
        onView(withId(R.id.performer_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_performerDetail() {
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isActivityPerformersInView() {
        onView(withId(R.id.performer_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_performerDetail_performerFound() {
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(typeText("gustavo"))
        onView(withId(R.id.perfomer_title_text)).perform(click())

        onView(withId(R.id.artist_image)).check(matches(isDisplayed()))
    }


    @Test
    fun test_visibility_performerDetail_performerImage() {
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(typeText("gustavo"))
        onView(withId(R.id.perfomer_title_text)).perform(click())

        onView(withId(R.id.artist_image)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_performerDetail_performerDescription() {
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(typeText("gustavo"))
        onView(withId(R.id.perfomer_title_text)).perform(click())

        onView(withId(R.id.artist_description_text)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_performerDetail_performerBirthday() {
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(typeText("gustavo"))
        onView(withId(R.id.perfomer_title_text)).perform(click())

        onView(withId(R.id.artist_birthdate_text)).check(matches(isDisplayed()))
    }


}