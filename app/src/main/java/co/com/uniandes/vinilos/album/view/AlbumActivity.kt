package co.com.uniandes.vinilos.album.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.AlbumListener
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.VinilosActivityBase
import co.com.uniandes.vinilos.album.view.adapter.AlbumViewAdapter
import co.com.uniandes.vinilos.album.viewModel.AlbumViewModel

class AlbumActivity : VinilosActivityBase(), AlbumListener {

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
        findViewById<RecyclerView>(R.id.album_recycler_view).apply {
            viewAdapter = AlbumViewAdapter(this@AlbumActivity, mutableListOf(), this@AlbumActivity)
            adapter = viewAdapter
            layoutManager = LinearLayoutManager(this@AlbumActivity)
        }
    }

    private fun setupSearchView() {
        findViewById<SearchView>(R.id.search_view).apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
    }

    private fun observeViewModel() {
        with(viewModel) {
            albums.observe(this@AlbumActivity) { albums ->
                viewAdapter.updateData(albums)
            }

            eventNetworkError.observe(this@AlbumActivity) { isNetworkError ->
                if (isNetworkError && isNetworkErrorShown.value == false) {
                    this@AlbumActivity.showError("Network error occurred")
                    onNetworkErrorShown()
                }
            }
        }
    }

    override fun openDetailAlbum(albumId: Int) {
        Log.d("AlbumActivity", "El Album seleccionado tiene id $albumId")
        Intent(this, CollectorDetailActivity::class.java).apply {
            putExtra("albumId", albumId)
            startActivity(this)
        }
    }
}
