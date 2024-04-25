package co.com.uniandes.vinilos.album.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.uniandes.vinilos.album.model.Album
import co.com.uniandes.vinilos.album.repository.AlbumRepository
import kotlinx.coroutines.launch

class AlbumViewModel2(private val repository: AlbumRepository) : ViewModel() {
    /*private val _albums = MutableLiveData<List<Album>>()
    val albums: MutableLiveData<List<Album>> get() = _albums

    fun fetchAlbums() {
        viewModelScope.launch {
            _albums.value = repository.getAlbums()
        }
    }*/
}