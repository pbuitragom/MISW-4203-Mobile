package co.com.uniandes.vinilos.album.repository

import AlbumServiceAdapter
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import co.com.uniandes.vinilos.album.model.Album
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AlbumRepositoryImpl(private val context: Context) : AlbumRepository {
    private val serviceAdapter = AlbumServiceAdapter(context)
    private var albums: List<Album> = emptyList()
    val albumsLiveData = MutableLiveData<List<Album>>()

    override fun getAlbum(): Album? {
        val liveData = MutableLiveData<Album>()

        /*val request = serviceAdapter.getAlbum(
            Response.Listener { response ->
                val gson = Gson()
                val album = gson.fromJson(response, Album::class.java)
                liveData.postValue(album)
            },
            Response.ErrorListener { error ->
                // Handle error
            }
        )
        serviceAdapter.instance.add(request)*/
        //return liveData
        return null
    }

    override fun getAlbums() {
        val albumListType = object : TypeToken<List<Album>>() {}.type
        val request = AlbumServiceAdapter.getAlbums(
            Response.Listener { response ->
                val gson = Gson()
                // Deserializar la respuesta y actualizar el LiveData
                val albums: List<Album> = gson.fromJson(response, albumListType)
                albumsLiveData.postValue(albums)  // Actualiza LiveData con la lista de álbumes
                Log.e("AlbumRepositoryImpl", "gson lo dejó como $albums")
            },
            Response.ErrorListener { error ->
                Log.e("AlbumRepositoryImpl", "Error: ${error.toString()}")
                albumsLiveData.postValue(emptyList())  // Publicar una lista vacía o manejar el error adecuadamente
            }
        )
        serviceAdapter.instance.add(request)
    }
}
