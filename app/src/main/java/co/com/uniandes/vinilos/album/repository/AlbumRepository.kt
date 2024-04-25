package co.com.uniandes.vinilos.album.repository

import AlbumServiceAdapter
import co.com.uniandes.vinilos.album.model.Album
import co.com.uniandes.vinilos.album.repository.adapters.AlbumService

class AlbumRepository(private val albumService: AlbumService) {
    suspend fun getAlbums(): List<Album> {
        return albumService.getAlbums()
    }
}
