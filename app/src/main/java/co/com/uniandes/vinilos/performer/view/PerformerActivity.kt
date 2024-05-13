package co.com.uniandes.vinilos.performer.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.PerformerListener
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.VinilosActivityBase
import co.com.uniandes.vinilos.performer.view.adapter.PerformerViewAdapter
import co.com.uniandes.vinilos.performer.viewModel.PerformerViewModel

class PerformerActivity : VinilosActivityBase(), PerformerListener {

    private lateinit var viewModel: PerformerViewModel
    private lateinit var viewAdapter: PerformerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_performers)
        initializeViewModel()
        setupRecyclerView()
        setupSearchView()
        observeViewModel()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this, PerformerViewModel.Factory(application))[PerformerViewModel::class.java]
    }

    private fun setupRecyclerView() {
        findViewById<RecyclerView>(R.id.performer_recycler_view).apply {
            viewAdapter = PerformerViewAdapter(this@PerformerActivity, mutableListOf(), this@PerformerActivity)
            adapter = viewAdapter
            layoutManager = LinearLayoutManager(this@PerformerActivity)
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
            albums.observe(this@PerformerActivity) { albums ->
                viewAdapter.updateData(albums)
            }

            eventNetworkError.observe(this@PerformerActivity) { isNetworkError ->
                if (isNetworkError && isNetworkErrorShown.value == false) {
                    // showError("Network error occurred")
                    onNetworkErrorShown()
                }
            }
        }
    }

    override fun openDetailPerformer(id: Int) {
        Log.d("PerformerActivity", "El Performer seleccionado es $id")
        Intent(this, PerformerDetailActivity::class.java).apply {
            putExtra("id", id)
            startActivity(this)
        }
    }
}
