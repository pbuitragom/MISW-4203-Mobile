import android.content.Intent
import android.view.MenuItem
import co.com.uniandes.vinilos.album.view.AlbumActivity
import co.com.uniandes.vinilos.RetrofitActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import co.com.uniandes.vinilos.R

class AlbumActivityTest {

    @Mock
    lateinit var mockMenuItem: MenuItem

    private lateinit var albumActivity: AlbumActivity

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        albumActivity = AlbumActivity()
    }

    @Test
    fun `Test onOptionsItemSelected switch layout`() {
        // Simular el ID del item del menú seleccionado como el de switch layout
        `when`(mockMenuItem.itemId).thenReturn(R.id.action_switch_layout)

        // Llamar al método onOptionsItemSelected con el mock del MenuItem
        val result = albumActivity.onOptionsItemSelected(mockMenuItem)

        // Verificar que se ha llamado startActivity con la intención adecuada
        val expectedIntent = Intent(albumActivity, RetrofitActivity::class.java)
        verify(albumActivity).startActivity(expectedIntent)

        // Verificar que onOptionsItemSelected devuelve verdadero
        Assert.assertTrue(result)
    }
}
