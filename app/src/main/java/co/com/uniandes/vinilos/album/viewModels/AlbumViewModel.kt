package co.com.uniandes.vinilos.album.viewModels

import AlbumServiceAdapter
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.com.uniandes.vinilos.album.model.Album
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AlbumViewModel(application: Application) :  AndroidViewModel(application) {

    lateinit var albumAdapter: AlbumServiceAdapter
    private val _albums = MutableLiveData<List<Album>>()

    val albums: LiveData<List<Album>>
        get() = _albums


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


        albumAdapter = AlbumServiceAdapter(getApplication<Application>().applicationContext)
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

        )
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