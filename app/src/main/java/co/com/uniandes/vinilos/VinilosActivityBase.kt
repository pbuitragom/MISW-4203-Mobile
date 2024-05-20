package co.com.uniandes.vinilos

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import co.com.uniandes.vinilos.album.view.AlbumActivity
import co.com.uniandes.vinilos.collector.view.CollectorActivity
import co.com.uniandes.vinilos.collector.view.CreateCollectorActivity
import co.com.uniandes.vinilos.performer.view.PerformerActivity

abstract class VinilosActivityBase : AppCompatActivity(){

    fun showError(message: String) {
        // Implementation to show error, e.g., a Toast or a Snackbar
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Infla el menú; esto agrega ítems a la barra de acciones si está presente.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_albums -> {
                val intent = Intent(this, AlbumActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.nav_performers -> {
                val intent = Intent(this, PerformerActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.nav_collectors -> {
                val intent = Intent(this, CollectorActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.nav_create_collector -> {
                val intent = Intent(this, CreateCollectorActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}