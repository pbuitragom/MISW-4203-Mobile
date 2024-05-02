package co.com.uniandes.vinilos.album.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.AlbumListener
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.album.model.Album
import co.com.uniandes.vinilos.album.view.adapter.AlbumViewAdapter
import co.com.uniandes.vinilos.album.viewModel.AlbumViewModel


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

    override fun openDetailAlbum(albumId: Int) {
        Log.e("AlbumActivity", "El Album seleccionado tiene id ${albumId}")
        Intent(this, AlbumDetailActivity::class.java).also {
            it.putExtra("albumId", albumId)
            startActivity(it)
        }
    }

    private fun showError(message: String) {
        // Implementation to show error, e.g., a Toast or a Snackbar
    }
}
