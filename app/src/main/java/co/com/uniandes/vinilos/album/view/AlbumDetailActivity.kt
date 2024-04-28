package co.com.uniandes.vinilos.album.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.com.uniandes.vinilos.album.model.Album
import co.com.uniandes.vinilos.databinding.ActivityAlbumDetailBinding
import com.squareup.picasso.Picasso

class AlbumDetailActivity : AppCompatActivity() {

    private var album: Album? = null
    private lateinit var binding: ActivityAlbumDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAlbum()
        setDescription()
    }

    private fun setAlbum() {
        val intentAlbum = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("album", Album::class.java)
        } else {
            intent.getSerializableExtra("album")
        }
        album = intentAlbum as? Album
    }

    @SuppressLint("SetTextI18n")
    private fun setDescription() {
        binding.apply {
            titleLabel.text = album?.name ?: "Album"
            Picasso.get().load(album?.cover ?: "https://www.labfriend.co.in/static/assets/images/shared/default-image.png").into(imageAlbum)
            titleAlbum.text = album?.name ?: "Album"
            releaseDateLabel.text = album?.releaseDate
            genderLabel.text = "Genero: ${album?.genre ?: "Desconocido"}"
            descriptionLabel.text = album?.description ?: "Descripción"
            commentButton.setOnClickListener {
                //TO-DO: Implementar acción del boton comentar
            }
            backImage.setOnClickListener {
                finish()
            }
            closeImage.setOnClickListener {
                finish()
            }
        }
    }

}