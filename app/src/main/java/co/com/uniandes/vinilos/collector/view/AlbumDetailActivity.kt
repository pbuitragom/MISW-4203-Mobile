package co.com.uniandes.vinilos.album.view

import android.os.Bundle
import android.util.Log
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

        setupUI()
        initializeViewModel()
        observeViewModel()

        val albumId: Int = intent.getIntExtra("albumId", -1)
        Log.d("AlbumDetailActivity", "El albumId es $albumId")
        if (albumId == -1) finish()

        viewModel.loadAlbum(albumId)
    }

    private fun setupUI() {
        with(binding) {
            backImage.setOnClickListener { finish() }
            closeImage.setOnClickListener { finish() }
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(application))[AlbumViewModel::class.java]
    }

    private fun observeViewModel() {
        viewModel.album.observe(this) { album ->
            album?.let {
                updateUI(it)
            }
        }
    }

    private fun updateUI(album: Album) {
        with(binding) {
            albumTitleText.text = album.name
            albumDescriptionText.text = album.description
            albumReleasedDateText.text = album.releaseDate
            albumGenreText.text = album.genre
            albumRecordLabelText.text = album.recordLabel
            Glide.with(this@AlbumDetailActivity).load(album.cover).into(albumCoverImage)
        }
    }
}
