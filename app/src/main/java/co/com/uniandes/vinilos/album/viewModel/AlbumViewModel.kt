package co.com.uniandes.vinilos.album.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.com.uniandes.vinilos.album.model.Album
import co.com.uniandes.vinilos.album.repository.AlbumRepository
import co.com.uniandes.vinilos.album.repository.AlbumRepositoryImpl
import com.bumptech.glide.load.engine.Resource

class AlbumViewModel(application: Application) :  AndroidViewModel(application) {

    private val repository: AlbumRepository  = AlbumRepositoryImpl(application)
    val albums: LiveData<List<Album>> = repository.getAlbums()

    private val _album = MutableLiveData<Album?>()
    val album: LiveData<Album?>
        get() = _album

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        Log.e("AlbumViewModel", "Acceso al Repository para llegar al AlbumServiceAdapter")
        repository.getAlbums()
    }

    fun loadAlbum(albumId: Int) {
        repository.getAlbum(albumId).observeForever { item ->
            _album.postValue(item)
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}