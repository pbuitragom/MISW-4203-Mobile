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

    val serviceAdapter = AlbumServiceAdapter(context)
    val albumsLiveData = MutableLiveData<List<Album>>()

    override fun getAlbum(): LiveData<Album> {
        val liveData = MutableLiveData<Album>()
        //Por implementar
        return liveData
    }

    override fun getAlbums(): LiveData<List<Album>> {
        val albumListType = object : TypeToken<List<Album>>() {}.type
        val request = AlbumServiceAdapter.getAlbums(
            { response ->
                val gson = Gson()
                val albums: List<Album> = gson.fromJson(response, albumListType)
                albumsLiveData.postValue(albums)
                Log.e("AlbumRepositoryImpl", "gson lo dejó como $albums")
            },
            { error ->
                Log.e("AlbumRepositoryImpl", "Error: ${error.toString()}")
                albumsLiveData.postValue(emptyList())
            }
        )
        serviceAdapter.instance.add(request)
        return albumsLiveData
    }


}
