package co.com.uniandes.vinilos.collector.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.collector.model.Collector
import co.com.uniandes.vinilos.collector.repository.CollectorRepositoryImpl
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.VinilosActivityBase
import co.com.uniandes.vinilos.collector.view.adapter.CollectorViewAdapter
import co.com.uniandes.vinilos.collector.viewModel.CollectorViewModel

class CreateCollectorActivity : VinilosActivityBase() {

    private lateinit var viewModel: CollectorViewModel
    private lateinit var editTextName: EditText
    private lateinit var editTextTelephone: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var buttonSave: Button
    private lateinit var viewAdapter: CollectorViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_collector)

        //viewModel = ViewModelProvider(this, CollectorViewModelFactory(application, CollectorRepositoryImpl(applicationContext))).get(CollectorViewModel::class.java)

        initializeViewModel()
        //setupRecyclerView()
        //observeViewModel()

        editTextName = findViewById(R.id.editTextName)
        editTextTelephone = findViewById(R.id.editTextTelephone)
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonSave = findViewById(R.id.buttonSave)

        viewModel.collectorCreatedEvent.observe(this, Observer { event ->
            if (event) {
                Toast.makeText(this, "Coleccionista guardado", Toast.LENGTH_SHORT).show()
                finish() // Cierra la actividad después de guardar
                viewModel.onCollectorCreatedHandled() // Resetear el evento
            }
        })

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val telephone = editTextTelephone.text.toString().trim()
            val email = editTextEmail.text.toString().trim()

            if (name.isNotEmpty() && telephone.isNotEmpty() && email.isNotEmpty()) {
                val collector = Collector(0, name, telephone, email)
                viewModel.saveCollector(collector)
                Toast.makeText(this, "Coleccionista guardado", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, CollectorActivity::class.java)
                startActivity(intent)
                finish() // Cierra la actividad después de guardar
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this, CollectorViewModel.Factory(application))[CollectorViewModel::class.java]
    }

    /*private fun observeViewModel() {
        with(viewModel) {
            collectors.observe(this@CreateCollectorActivity) { items ->
                viewAdapter.updateData(items)
            }

            eventNetworkError.observe(this@CreateCollectorActivity) { isNetworkError ->
                if (isNetworkError && isNetworkErrorShown.value == false) {
                    this@CreateCollectorActivity.showError("Network error occurred")
                    onNetworkErrorShown()
                }
            }
        }
    }*/
}
