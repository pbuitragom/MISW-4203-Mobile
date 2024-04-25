package co.com.uniandes.vinilos.album.repository

import androidx.lifecycle.LiveData
import co.com.uniandes.vinilos.album.model.Album

interface AlbumRepository{

    fun getAlbum(): LiveData<Album>
    fun getAlbums():  LiveData<List<Album>>
}
