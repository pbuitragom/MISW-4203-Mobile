package co.com.uniandes.vinilos.collector.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.collector.view.adapter.CollectorViewAdapter
import org.junit.Rule
import org.junit.Test

class CollectorActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(CollectorActivity::class.java)

    @Test
    fun test_visibility_toolbar() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_collectorList() {
        onView(withId(R.id.collector_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_searchView() {
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(withId(androidx.appcompat.R.id.search_button)).check(matches(isDisplayed()))
    }

    @Test
    fun test_search_functionality() {
        Thread.sleep(2000)
        onView(withId(androidx.appcompat.R.id.search_button)).perform(click())
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText("Argemiro"))
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(pressImeActionButton())

        onView(withId(R.id.collector_name)).check(matches(isDisplayed()))
        onView(withId(R.id.collector_name)).check(matches(withText("Argemiro")))
    }

    @Test
    fun test_navigate_to_collectorDetail() {
        Thread.sleep(2000)
        onView(withId(androidx.appcompat.R.id.search_button)).perform(click())
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText("Argemiro"))
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(pressImeActionButton())

        onView(withId(R.id.collector_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CollectorViewAdapter.CollectorViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(R.id.collector_image)).check(matches(isDisplayed()))
    }
}
