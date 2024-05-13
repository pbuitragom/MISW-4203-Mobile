package co.com.uniandes.vinilos.performer.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.com.uniandes.vinilos.performer.model.Performer
import co.com.uniandes.vinilos.performer.repository.PerformerRepository
import co.com.uniandes.vinilos.performer.repository.PerformerRepositoryImpl

class PerformerViewModel(application: Application) :  AndroidViewModel(application) {

    private val repository: PerformerRepository = PerformerRepositoryImpl(application)
    val albums: LiveData<List<Performer>> = repository.getPerformers()

    val _performer = MutableLiveData<Performer?>()
    val performer: LiveData<Performer?>
        get() = _performer

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
        Log.e("PerformerViewModel", "Acceso al Repository para llegar al PerformerServiceAdapter")
        repository.getPerformers()
    }

    fun loadPerformer(id: Int) {
        repository.getPerformer(id).observeForever { item ->
            _performer.postValue(item)
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PerformerViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PerformerViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}