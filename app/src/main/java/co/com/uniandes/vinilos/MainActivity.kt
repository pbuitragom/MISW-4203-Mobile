package co.com.uniandes.vinilos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.album.view.adapter.AlbumViewAdapter
import co.com.uniandes.vinilos.album.viewModels.AlbumViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: AlbumViewModel
    private lateinit var viewAdapter: AlbumViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_albums)
        // Instanciación del ViewModel
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(application))[AlbumViewModel::class.java]

        val searchView = findViewById<SearchView>(R.id.search_view)

        val recyclerView = findViewById<RecyclerView>(R.id.album_recycler_view)
        viewAdapter = AlbumViewAdapter(this, mutableListOf())
        recyclerView.adapter = viewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observar los cambios en los datos del ViewModel
        viewModel.albums.observe(this, Observer { albumList ->
            viewAdapter.updateData(albumList.toMutableList())
            recyclerView.adapter = viewAdapter
            val currentQuery = searchView.query.toString()
            Log.e("MainActivity", "Texto para buscar ${currentQuery}")
            viewAdapter.filter(currentQuery)
        })


        viewModel.eventNetworkError.observe(this, Observer { isNetworkError ->
            if (isNetworkError) {
                if (!viewModel.isNetworkErrorShown.value!!) {
                    //showErrorDialog()
                    //getResultTextView.text = "That didn't work!"
                    viewModel.onNetworkErrorShown()
                }
            }
        })

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

    private fun showErrorDialog() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Create an intent with a destination of the other Activity
                val intent = Intent(this, RetrofitActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}


