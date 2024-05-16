package co.com.uniandes.vinilos.collector.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import co.com.uniandes.vinilos.VinilosActivityBase
import co.com.uniandes.vinilos.collector.model.Collector
import co.com.uniandes.vinilos.collector.viewModel.CollectorViewModel
import co.com.uniandes.vinilos.databinding.ActivityCollectorDetailBinding

class CollectorDetailActivity : VinilosActivityBase() {

    private var item: Collector? = null
    private lateinit var viewModel: CollectorViewModel
    private lateinit var binding: ActivityCollectorDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        initializeViewModel()
        observeViewModel()

        val id: Int = intent.getIntExtra("id", -1)
        Log.d("CollectorDetailActivity", "El id es $id")
        if (id == -1) finish()

        viewModel.loadCollector(id)
    }

    private fun setupUI() {
        with(binding) {
            backImage.setOnClickListener { finish() }
            closeImage.setOnClickListener { finish() }
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this, CollectorViewModel.Factory(application))[CollectorViewModel::class.java]
    }

    private fun observeViewModel() {
        viewModel.collector.observe(this) { item ->
            item?.let {
                updateUI(it)
            }
        }
    }

    private fun updateUI(item: Collector) {
        with(binding) {


            collectorName.text = item.name
            collectorEmailText.text = item.email
            collectorTelephoneText.text = item.telephone


        }
    }
}
