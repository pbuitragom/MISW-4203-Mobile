package co.com.uniandes.vinilos
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class AlbumRules {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `Listando reglas de Album`() = coroutinesTestRule.testDispatcher.runBlockingTest{
        Assert.assertTrue(true)
    }
}