package co.com.uniandes.vinilos.performer.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.PerformerListener
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.VinilosActivityBase
import co.com.uniandes.vinilos.performer.model.Performer
import co.com.uniandes.vinilos.performer.view.adapter.PerformerViewAdapter
import co.com.uniandes.vinilos.performer.viewModel.PerformerViewModel


class PerformerActivity : VinilosActivityBase(),  PerformerListener {

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
        val recyclerView = findViewById<RecyclerView>(R.id.performer_recycler_view)
        viewAdapter = PerformerViewAdapter(this, mutableListOf(), this)
        recyclerView.apply {
            adapter = viewAdapter
            layoutManager = LinearLayoutManager(this@PerformerActivity)
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
                //showError("Network error occurred")
                viewModel.onNetworkErrorShown()
            }
        }
    }

    override fun openDetailPerformer(id: Int) {
        Log.e("PerformerActivity", "El Performer seleccionado es ${id} ")
        Intent(this, PerformerDetailActivity::class.java).also {
            it.putExtra("id", id)
            startActivity(it)
        }
    }
}
