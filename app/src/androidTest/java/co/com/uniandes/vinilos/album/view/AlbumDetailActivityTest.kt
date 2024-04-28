package co.com.uniandes.vinilos.album.view

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.album.model.Album
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test


class AlbumDetailActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AlbumActivity::class.java)


    @Test
    fun test_visibility_albumDetail() {
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(
            ViewActions.typeText(
                "buscando"
            )
        )
        onView(withId(R.id.album_cover_image)).check(matches(isDisplayed()))
        onView(withId(R.id.album_cover_image)).perform(click())

        onView(withId(R.id.toolbarLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_albumDetailImage() {
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(
            ViewActions.typeText(
                "buscando"
            )
        )
        onView(withId(R.id.album_cover_image)).check(matches(isDisplayed()))
        onView(withId(R.id.album_cover_image)).perform(click())

        onView(withId(R.id.imageAlbum)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_albumDetailTitle() {
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(
            ViewActions.typeText(
                "buscando"
            )
        )
        onView(withId(R.id.album_cover_image)).check(matches(isDisplayed()))
        onView(withId(R.id.album_cover_image)).perform(click())

        onView(withId(R.id.titleAlbum)).check(matches(isDisplayed()))
        onView(withId(R.id.titleAlbum)).check(matches(withText("Buscando América")))
    }

    @Test
    fun test_visibility_albumDetailGenderLabel() {
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(
            ViewActions.typeText(
                "buscando"
            )
        )
        onView(withId(R.id.album_cover_image)).check(matches(isDisplayed()))
        onView(withId(R.id.album_cover_image)).perform(click())

        onView(withId(R.id.genderLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.genderLabel)).check(matches(withText("Genero: Salsa")))
    }

    @Test
    fun test_visibility_albumDetailDescriptionLabel() {
        onView(withId(androidx.constraintlayout.widget.R.id.search_button)).perform(click())
        onView(withId(androidx.constraintlayout.widget.R.id.search_src_text)).perform(
            ViewActions.typeText(
                "buscando"
            )
        )
        onView(withId(R.id.album_cover_image)).check(matches(isDisplayed()))
        onView(withId(R.id.album_cover_image)).perform(click())

        onView(withId(R.id.descriptionLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.descriptionLabel)).check(matches(withText("Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.")))
    }

}