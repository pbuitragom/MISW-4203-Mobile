package co.com.uniandes.vinilos

import co.com.uniandes.vinilos.album.model.Album
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class AlbumTest {

    @Test
    fun `Creación correcta del Album`() {
        val album = Album(
            albumId = 1,
            name = "Example Album",
            cover = "example_cover.jpg",
            releaseDate = "2024-04-28",
            description = "This is an example album.",
            genre = "Rock",
            recordLabel = "Example Records"
        )

        assertEquals(1, album.albumId)
        assertEquals("Example Album", album.name)
        assertEquals("example_cover.jpg", album.cover)
        assertEquals("2024-04-28", album.releaseDate)
        assertEquals("This is an example album.", album.description)
        assertEquals("Rock", album.genre)
        assertEquals("Example Records", album.recordLabel)
    }

    @Test
    fun `Serialización correcta del Album`() {
        val album = Album(
            albumId = 1,
            name = "Example Album",
            cover = "example_cover.jpg",
            releaseDate = "2024-04-28",
            description = "This is an example album.",
            genre = "Rock",
            recordLabel = "Example Records"
        )

        val gson = Gson()
        val jsonString = gson.toJson(album)
        val deserializedAlbum = gson.fromJson(jsonString, Album::class.java)

        assertEquals(album, deserializedAlbum)
    }


}
