package co.com.uniandes.vinilos.album.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.AlbumListener
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.album.model.Album
import co.com.uniandes.vinilos.album.view.adapter.AlbumViewAdapter
import co.com.uniandes.vinilos.album.viewModel.AlbumViewModel


/*class AlbumActivity : AppCompatActivity(), AlbumListener {

    private lateinit var viewModel: AlbumViewModel
    private lateinit var viewAdapter: AlbumViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_albums)
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(application))[AlbumViewModel::class.java]

        val searchView = findViewById<SearchView>(R.id.search_view)

        val recyclerView = findViewById<RecyclerView>(R.id.album_recycler_view)
        viewAdapter = AlbumViewAdapter(this, mutableListOf(), this)
        recyclerView.adapter = viewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observar los cambios en los datos del ViewModel
        viewModel.albums.observe(this) { albumList ->
            viewAdapter.updateData(albumList.toMutableList())
            recyclerView.adapter = viewAdapter
            val currentQuery = searchView.query.toString()
            Log.e("MainActivity", "Texto para buscar $currentQuery")
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

    override fun openDetailAlbum(album: Album) {
        val intent = Intent(this, AlbumDetailActivity::class.java)
        intent.putExtra("album", album)
        startActivity(intent)
    }

}*/

class AlbumActivity : AppCompatActivity(), AlbumListener {

    private lateinit var viewModel: AlbumViewModel
    private lateinit var viewAdapter: AlbumViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        initializeViewModel()
        setupRecyclerView()
        setupSearchView()
        observeViewModel()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(application))[AlbumViewModel::class.java]
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.album_recycler_view)
        viewAdapter = AlbumViewAdapter(this, mutableListOf(), this)
        recyclerView.apply {
            adapter = viewAdapter
            layoutManager = LinearLayoutManager(this@AlbumActivity)
        }
    }

    private fun setupSearchView() {
        val searchView = findViewById<SearchView>(R.id.search_view)
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

    private fun observeViewModel() {
        viewModel.albums.observe(this) { albums ->
            viewAdapter.updateData(albums.toMutableList())
        }

        viewModel.eventNetworkError.observe(this) { isNetworkError ->
            if (isNetworkError && !viewModel.isNetworkErrorShown.value!!) {
                showError("Network error occurred")
                viewModel.onNetworkErrorShown()
            }
        }
    }

    override fun openDetailAlbum(album: Album) {
        Intent(this, AlbumDetailActivity::class.java).also {
            it.putExtra("album", album)
            startActivity(it)
        }
    }

    private fun showError(message: String) {
        // Implementation to show error, e.g., a Toast or a Snackbar
    }
}
