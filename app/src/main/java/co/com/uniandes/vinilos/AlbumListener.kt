package co.com.uniandes.vinilos

import co.com.uniandes.vinilos.album.model.Album

interface AlbumListener {
    fun openDetailAlbum(album: Album)
}