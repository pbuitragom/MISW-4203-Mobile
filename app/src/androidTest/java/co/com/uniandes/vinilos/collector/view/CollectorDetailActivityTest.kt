package co.com.uniandes.vinilos.collector.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.collector.view.adapter.CollectorViewAdapter
import org.junit.Rule
import org.junit.Test

class CollectorDetailActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(CollectorActivity::class.java)

    @Test
    fun test_visibility_collectorDetail() {
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

    @Test
    fun test_click_backImage() {
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

        onView(withId(R.id.backImage)).perform(click())
    }

    @Test
    fun test_display_collectorEmail() {
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

        onView(withId(R.id.collector_email_text)).check(matches(isDisplayed()))
    }

    @Test
    fun test_display_collectorTelephone() {
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

        onView(withId(R.id.collector_telephone_text)).check(matches(isDisplayed()))
    }
}
