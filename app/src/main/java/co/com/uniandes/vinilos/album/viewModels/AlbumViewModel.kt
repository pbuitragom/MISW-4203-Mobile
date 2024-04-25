package co.com.uniandes.vinilos.album.viewModels

import AlbumServiceAdapter
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AlbumViewModel(application: Application) :  AndroidViewModel(application) {

    //Funciona lateinit var albumAdapter: AlbumServiceAdapter
    private val repository = AlbumRepositoryImpl(application)
    val albums: LiveData<List<Album>> = repository.albumsLiveData

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

        //Retrofit
        //SealedClass??
            //Gestión de errores, con el output para gestionar httpcodes de retorno

        Log.e("AlbumViewModel", "Acceso al Repository para llegar al AlbumServiceAdapter")
        /*albumAdapter = AlbumServiceAdapter(getApplication<Application>().applicationContext)
        albumAdapter.instance.add (AlbumServiceAdapter.getAlbums(
            {
                val gson = Gson()
                val albumListType = object : TypeToken<List<Album>>() {}.type
                val albums: List<Album> = gson.fromJson(it, albumListType)
                _albums.postValue(albums)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            }
            ,{
                _eventNetworkError.value = true
            })

        )*/
        repository.getAlbums()
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