package co.com.uniandes.vinilos.album.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.album.model.Album
import co.com.uniandes.vinilos.album.viewModel.AlbumViewModel
import com.bumptech.glide.Glide

class AlbumDetailActivityPM : AppCompatActivity() {

    private lateinit var viewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pm_activity_album_detail)

        val albumId: Int = intent.getIntExtra("albumId", -1 )
        Log.e("AlbumDetailActivityPM", "El albumId es ${albumId}")
        if (albumId == -1) finish() // Terminate if no valid ID provided

        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(application))[AlbumViewModel::class.java]

        val albumTitle = findViewById<TextView>(R.id.album_title_text)
        val albumDescription = findViewById<TextView>(R.id.album_description_text)
        val albumCover = findViewById<ImageView>(R.id.album_cover_image)

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

        albumTitle.text = album.name
        albumDescription.text = album.description
        Glide.with(this).load(album.cover).into(albumCover)
    }
}
