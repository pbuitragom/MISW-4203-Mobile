package co.com.uniandes.vinilos.album.repository

import co.com.uniandes.vinilos.album.model.Album

interface AlbumRepository{

    fun getAlbum(): Album?
    fun getAlbums()
}
