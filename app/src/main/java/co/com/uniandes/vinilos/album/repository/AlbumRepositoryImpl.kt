package co.com.uniandes.vinilos.album.repository

import AlbumServiceAdapter
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import co.com.uniandes.vinilos.album.model.Album
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers

class AlbumRepositoryImpl(private val context: Context) : AlbumRepository {

    override fun getAlbum(albumId: Int): LiveData<Album?> = liveData(Dispatchers.IO) {
        val response = AlbumServiceAdapter.getAlbum(albumId)
        val album = response?.let {
            try {
                Gson().fromJson(it, Album::class.java)
            } catch (e: Exception) {
                Log.e("AlbumRepositoryImpl", "Error parsing album", e)
                null
            }
        }
        emit(album)
    }

    override fun getAlbums(): LiveData<List<Album>> = liveData(Dispatchers.IO) {
        val response = AlbumServiceAdapter.getAlbums()
        val gson = Gson()
        val albumListType = object : TypeToken<List<Album>>() {}.type
        val albums: List<Album> = response?.let {
            try {
                if (it.startsWith("[")) {
                    gson.fromJson(it, albumListType)
                } else {
                    val singleAlbum: Album = gson.fromJson(it, Album::class.java)
                    listOf(singleAlbum)
                }
            } catch (e: Exception) {
                Log.e("AlbumRepositoryImpl", "Error parsing albums", e)
                emptyList()
            }
        } ?: emptyList()
        emit(albums)
    }

}
