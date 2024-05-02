package co.com.uniandes.vinilos.album.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.VinilosActivityBase
import co.com.uniandes.vinilos.album.model.Album
import co.com.uniandes.vinilos.album.viewModel.AlbumViewModel
import co.com.uniandes.vinilos.databinding.ActivityAlbumDetailBinding
import com.bumptech.glide.Glide

class AlbumDetailActivity : VinilosActivityBase() {

    private var album: Album? = null
    private lateinit var viewModel: AlbumViewModel
    private lateinit var binding: ActivityAlbumDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backImage.setOnClickListener {
            finish()
        }
        binding.closeImage.setOnClickListener {
            finish()
        }

        val albumId: Int = intent.getIntExtra("albumId", -1 )
        Log.e("AlbumDetailActivity", "El albumId es ${albumId}")
        if (albumId == -1) finish() // Terminate if no valid ID provided

        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(application))[AlbumViewModel::class.java]

        viewModel.loadAlbum(albumId)

        viewModel.album.observe(this) { album ->
            album?.let {
                updateUI(it)
            }
        }

    }

    private fun updateUI(album: Album) {
        val albumTitle = findViewById<TextView>(R.id.album_title_text)
        val albumDescription = findViewById<TextView>(R.id.album_description_text)
        val albumCover = findViewById<ImageView>(R.id.album_cover_image)
        val albumReleaseDate = findViewById<TextView>(R.id.album_released_date_text)
        val albumGenre = findViewById<TextView>(R.id.album_genre_text)
        val albumRecordLabel = findViewById<TextView>(R.id.album_record_label_text)

        albumTitle.text = album.name
        albumDescription.text = album.description
        albumReleaseDate.text = album.releaseDate
        albumGenre.text = album.genre
        albumRecordLabel.text = album.recordLabel
        Glide.with(this).load(album.cover).into(albumCover)
    }
}