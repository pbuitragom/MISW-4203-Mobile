package co.com.uniandes.vinilos.album.repository

import AlbumServiceAdapter
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.com.uniandes.vinilos.album.model.Album
import co.com.uniandes.vinilos.album.repository.AlbumRepository

class MockAlbumRepositoryImpl(private val context: Context) : AlbumRepository {

    private val serviceAdapter = AlbumServiceAdapter(context)
    val albumsLiveData = MutableLiveData<List<Album>>()

    override fun getAlbum(albumId: Int): LiveData<Album?> {
        val liveData = MutableLiveData<Album>()
        return liveData
    }

    override fun getAlbums(): LiveData<List<Album>> {

        val albumList = mutableListOf(
            Album(
                albumId = 1,
                name = "The Dark Side of the Moon",
                cover = "https://link-to-image.com/darkside.jpg",
                releaseDate = "1973-03-01",
                description = "The eighth studio album by the English rock band Pink Floyd.",
                genre = "Rock",
                recordLabel = "Harvest Records"
            ),
            Album(
                albumId = 2,
                name = "Thriller",
                cover = "https://link-to-image.com/thriller.jpg",
                releaseDate = "1982-11-30",
                description = "The sixth studio album by American singer Michael Jackson.",
                genre = "Pop, rock, R&B",
                recordLabel = "Epic Records"
            )
        )

        // Imprimir la lista de álbumes para verificación
        albumList.forEach { album ->
            println("Album: ${album.name}, Genre: ${album.genre}")
        }

        albumsLiveData.postValue(albumList)
        return albumsLiveData

    }


}
