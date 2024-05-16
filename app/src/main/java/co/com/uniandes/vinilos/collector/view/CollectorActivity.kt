package co.com.uniandes.vinilos.collector.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.CollectorListener
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.VinilosActivityBase
import co.com.uniandes.vinilos.collector.viewModel.CollectorViewModel

class CollectorActivity : VinilosActivityBase(), CollectorListener {

    private lateinit var viewModel: CollectorViewModel
    private lateinit var viewAdapter: CollectorviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collector)

        initializeViewModel()
        setupRecyclerView()
        setupSearchView()
        observeViewModel()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this, CollectorViewModel.Factory(application))[CollectorViewModel::class.java]
    }

    private fun setupRecyclerView() {
        findViewById<RecyclerView>(R.id.collector_recycler_view).apply {
            viewAdapter = CollectorViewAdapter(this@AlbumActivity, mutableListOf(), this@CollectorActivity)
            adapter = viewAdapter
            layoutManager = LinearLayoutManager(this@CollectorActivity)
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
            collectors.observe(this@CollectorActivity) { items ->
                viewAdapter.updateData(items)
            }

            eventNetworkError.observe(this@CollectorActivity) { isNetworkError ->
                if (isNetworkError && isNetworkErrorShown.value == false) {
                    this@CollectorActivity.showError("Network error occurred")
                    onNetworkErrorShown()
                }
            }
        }
    }

    override fun openDetailCollector(id: Int) {
        Log.d("CollectorActivity", "El Album seleccionado tiene id $id")
        Intent(this, CollectorDetailActivity::class.java).apply {
            putExtra("id", id)
            startActivity(this)
        }
    }
}
