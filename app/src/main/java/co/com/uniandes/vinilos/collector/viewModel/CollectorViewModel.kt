package co.com.uniandes.vinilos.collector.viewModel

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import co.com.uniandes.vinilos.collector.model.Collector
import co.com.uniandes.vinilos.collector.repository.CollectorRepository
import co.com.uniandes.vinilos.collector.repository.CollectorRepositoryImpl
import co.com.uniandes.vinilos.collector.view.CollectorActivity
import kotlinx.coroutines.launch

class CollectorViewModel(application: Application) :  AndroidViewModel(application) {

    private val repository: CollectorRepository = CollectorRepositoryImpl(application)
    val collectors: LiveData<List<co.com.uniandes.vinilos.collector.model.Collector>> = repository.getCollectors()

    private val _collectors = MutableLiveData<List<Collector>>()
    private val _collectorCreatedEvent = MutableLiveData<Boolean>()
    val collectorCreatedEvent: LiveData<Boolean> get() = _collectorCreatedEvent

    private val _collector = MutableLiveData<Collector?>()
    val collector: LiveData<Collector?>
        get() = _collector

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        fetchCollectors()
        refreshDataFromNetwork()
    }


    private fun fetchCollectors() {
        viewModelScope.launch {
            val collectorsList = repository.getCollectors()
            _collectors.postValue(collectorsList.value)
        }
    }

    private fun refreshDataFromNetwork() {
        Log.e("CollectorViewModel", "Acceso al Repository para llegar al CollectorServiceAdapter")
        repository.getCollectors()
    }

    fun loadCollector(id: Int) {
        repository.getCollector(id).observeForever { item ->
            _collector.postValue(item)
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CollectorViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

    fun saveCollector(collector: Collector) {
        viewModelScope.launch {
            val result = repository.save(collector)
            if (result) {
                fetchCollectors()
                _collectorCreatedEvent.postValue(true) // Notificar que se ha creado un coleccionista
            }
            if (!result) {
                _isNetworkErrorShown.value = true
            }
        }
    }

    fun onCollectorCreatedHandled() {
        _collectorCreatedEvent.value = false
    }
}