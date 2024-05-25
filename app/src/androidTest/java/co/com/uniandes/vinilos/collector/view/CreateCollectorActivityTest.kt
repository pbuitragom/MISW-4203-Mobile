package co.com.uniandes.vinilos.collector.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.collector.view.adapter.CollectorViewAdapter
import org.junit.Rule
import org.junit.Test

class CreateCollectorActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(CreateCollectorActivity::class.java)

    @Test
    fun test_visibility_createCollector() {
        onView(withId(R.id.editTextName)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextTelephone)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextEmail)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonSave)).check(matches(isDisplayed()))
    }

    @Test
    fun test_enterCollectorName() {
        onView(withId(R.id.editTextName)).perform(typeText("John Doe"))
        onView(withId(R.id.editTextName)).check(matches(withText("John Doe")))
    }

    @Test
    fun test_enterCollectorTelephone() {
        onView(withId(R.id.editTextTelephone)).perform(typeText("1234567890"))
        onView(withId(R.id.editTextTelephone)).check(matches(withText("1234567890")))
    }

    @Test
    fun test_enterCollectorEmail() {
        onView(withId(R.id.editTextEmail)).perform(typeText("john.doe@example.com"))
        onView(withId(R.id.editTextEmail)).check(matches(withText("john.doe@example.com")))
    }

    @Test
    fun test_clickSaveButton() {
        onView(withId(R.id.editTextName)).perform(typeText("John Doe"))
        onView(withId(R.id.editTextTelephone)).perform(typeText("1234567890"))
        onView(withId(R.id.editTextEmail)).perform(typeText("john.doe@example.com"))
        onView(withId(R.id.buttonSave)).perform(click())
    }

    @Test
    fun test_saveAndSearchCollectorByName() {
        onView(withId(R.id.editTextName)).perform(typeText("John Doe"))
        onView(withId(R.id.editTextTelephone)).perform(typeText("1234567890"))
        onView(withId(R.id.editTextEmail)).perform(typeText("john.doe@example.com"))
        onView(withId(R.id.buttonSave)).perform(click())

        Thread.sleep(2000)
        onView(withId(androidx.appcompat.R.id.search_button)).perform(click())
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText("John Doe"))
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(ViewActions.pressImeActionButton())
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
