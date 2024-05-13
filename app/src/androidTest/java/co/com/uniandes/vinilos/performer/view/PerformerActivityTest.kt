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
import org.junit.Rule
import org.junit.Test

class PerformerActivityTest {

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
    fun test_visibility_performerSearch() {
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isActivityPerformersInView() {
        onView(withId(R.id.performer_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_performerSearch_performerFound() {
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(typeText("gustavo"))
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(withId(R.id.perfomer_title_text)).check(matches(withText("Gustavo Cerati|")))
    }

    @Test
    fun test_performSearch_PerformerViewNotDisplayed() {
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(typeText("gustavo"))
        onView(withId(R.id.performer_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_performerSearch_performerCover() {
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(typeText("gustavo"))
        onView(withId(R.id.perfomer_cover_image)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_performerSearch_performerTitle() {
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(typeText("gustavo"))
        onView(withId(R.id.perfomer_title_text)).check(matches(isDisplayed()))
        onView(withId(R.id.perfomer_title_text)).check(matches(withText("Gustavo Cerati|")))
    }

    @Test
    fun test_visibility_performerSearch_performerDescription() {
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(typeText("gustavo"))
        onView(withId(R.id.perfomer_details_text)).check(matches(isDisplayed()))
        onView(withId(R.id.perfomer_details_text)).check(matches(withText("Es un cantautor, compositor, actor, escritor, poeta y músico argentino.")))
    }
}