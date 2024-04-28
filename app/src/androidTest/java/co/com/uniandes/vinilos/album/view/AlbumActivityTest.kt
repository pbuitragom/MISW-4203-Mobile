package co.com.uniandes.vinilos.album.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
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

class AlbumActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(AlbumActivity::class.java)

    @Test
    fun test_visibility_albumTitle() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_albumList() {
        onView(withId(R.id.album_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_albumSearch() {
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_action_albumSearch() {
        // Buscar el album con el nombre buscando
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(typeText("buscando"))

        // Verificar que se muestra el album buscando
        onView(withId(R.id.album_recycler_view)).check(matches(isDisplayed()))
        onView(withId(R.id.album_title_text)).check(matches(withText("Buscando América")))
    }
}