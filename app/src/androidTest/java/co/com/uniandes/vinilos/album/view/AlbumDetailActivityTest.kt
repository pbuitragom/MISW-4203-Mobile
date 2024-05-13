package co.com.uniandes.vinilos.album.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import co.com.uniandes.vinilos.R
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withText
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

        onView(withId(R.id.album_cover_image)).check(matches(isDisplayed()))
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

        onView(withId(R.id.album_title_text)).check(matches(isDisplayed()))
        onView(withId(R.id.album_title_text)).check(matches(withText("Buscando América")))
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

        onView(withId(R.id.album_genre_text)).check(matches(isDisplayed()))
        onView(withId(R.id.album_genre_text)).check(matches(withText("Genero: Salsa")))
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

        onView(withId(R.id.album_description_text)).check(matches(isDisplayed()))
        onView(withId(R.id.album_description_text)).check(matches(withText("Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.")))
    }

}