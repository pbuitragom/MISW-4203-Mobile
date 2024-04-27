package co.com.uniandes.vinilos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.album.model.Album
import co.com.uniandes.vinilos.album.view.AlbumActivity
import co.com.uniandes.vinilos.album.view.adapter.AlbumViewAdapter
import co.com.uniandes.vinilos.album.viewModels.AlbumViewModel

class MainActivity : AppCompatActivity(), AlbumListener {
    private lateinit var viewModel: AlbumViewModel
    private lateinit var viewAdapter: AlbumViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_albums)
        // Instanciación del ViewModel
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(application))[AlbumViewModel::class.java]

        val searchView = findViewById<SearchView>(R.id.search_view)

        setupRecyclerView()

        // Observar los cambios en los datos del ViewModel
        val recyclerView = findViewById<RecyclerView>(R.id.album_recycler_view)
        viewModel.albums.observe(this) { albumList ->
            viewAdapter.updateData(albumList.toMutableList())
            recyclerView.adapter = viewAdapter
            val currentQuery = searchView.query.toString()
            Log.e("MainActivity", "Texto para buscar ${currentQuery}")
            viewAdapter.filter(currentQuery)
        }


        viewModel.eventNetworkError.observe(this) { isNetworkError ->
            if (isNetworkError) {
                if (!viewModel.isNetworkErrorShown.value!!) {
                    viewModel.onNetworkErrorShown()
                }
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewAdapter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewAdapter.filter(newText)
                return true
            }
        })
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.album_recycler_view)
        viewAdapter = AlbumViewAdapter(this, mutableListOf(), this)
        recyclerView.adapter = viewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
//                 Create an intent with a destination of the other Activity
                val intent = Intent(this, RetrofitActivity::class.java)
                startActivity(intent)
//                val album = Album(
//                    albumId = 1,
//                    name =  "Buscando América",
//                    cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
//                    releaseDate = "1984-08-01T05:00:00.000Z",
//                    description = "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.",
//                    genre = "Salsa",
//                    recordLabel = "Elektra"
//                )
//                val intent = Intent(this, AlbumDetailActivity::class.java)
//                intent.putExtra("album", album)
//                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun openDetailAlbum(album: Album) {
        val intent = Intent(this, AlbumActivity::class.java)
        intent.putExtra("album", album)
        startActivity(intent)
    }


}


