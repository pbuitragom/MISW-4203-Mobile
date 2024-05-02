package co.com.uniandes.vinilos.performer.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.databinding.ActivityAlbumDetailBinding
import co.com.uniandes.vinilos.databinding.ActivityPerformerDetailBinding
import co.com.uniandes.vinilos.databinding.ActivityPerformersBinding
import co.com.uniandes.vinilos.performer.model.Performer
import co.com.uniandes.vinilos.performer.viewModel.PerformerViewModel
import com.bumptech.glide.Glide

class PerformerDetailActivity : AppCompatActivity() {

    private var performer: Performer? = null
    private lateinit var viewModel: PerformerViewModel
    private lateinit var binding: ActivityPerformerDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerformerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backImage.setOnClickListener {
            finish()
        }
        binding.closeImage.setOnClickListener {
            finish()
        }

        val id: Int = intent.getIntExtra("id", -1 )
        Log.e("PerformerDetailActivity", "El Id del artista es ${id}")
        if (id == -1) finish() // Terminate if no valid ID provided

        viewModel = ViewModelProvider(this, PerformerViewModel.Factory(application))[PerformerViewModel::class.java]

        viewModel.loadPerformer(id)

        viewModel.performer.observe(this) { perf ->
            perf?.let {
                updateUI(it)
            }
        }

    }

    private fun updateUI(performer: Performer) {
        val name = findViewById<TextView>(R.id.artist_name_text)
        val description = findViewById<TextView>(R.id.artist_description_text)
        val image = findViewById<ImageView>(R.id.artist_image)
        val birthDate = findViewById<TextView>(R.id.artist_birthdate_text)

        name.text = performer.name
        description.text = performer.description
        birthDate.text = performer.birthDate
        Glide.with(this).load(performer.image).into(image)

    }

}