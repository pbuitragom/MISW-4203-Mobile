package co.com.uniandes.vinilos.collector.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import co.com.uniandes.vinilos.collector.model.Collector
import co.com.uniandes.vinilos.collector.repository.adapters.CollectorServiceAdapter
import io.ktor.client.statement.HttpResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CollectorRepositoryImpl(private val context: Context) : CollectorRepository {

    override fun getCollector(id: Int): LiveData<Collector?> = liveData(Dispatchers.IO) {
        val response = CollectorServiceAdapter.getCollector(id)
        val album = response?.let {
            try {
                Gson().fromJson(it, Collector::class.java)
            } catch (e: Exception) {
                Log.e("CollectorRepositoryImpl", "Error parsing album", e)
                null
            }
        }
        emit(album)
    }

    override fun getCollectors(): LiveData<List<Collector>> = liveData(Dispatchers.IO) {
        val response = CollectorServiceAdapter.getCollectors()
        val gson = Gson()
        val collectorListType = object : TypeToken<List<Collector>>() {}.type
        val collectors: List<Collector> = response?.let {
            try {
                if (it.startsWith("[")) {
                    gson.fromJson(it, collectorListType)
                } else {
                    val singleCollector: Collector = gson.fromJson(it, Collector::class.java)
                    listOf(singleCollector)
                }
            } catch (e: Exception) {
                Log.e("CollectorRepositoryImpl", "Error parsing albums", e)
                emptyList()
            }
        } ?: emptyList()
        emit(collectors)
    }

    override suspend fun save(collector: Collector): Boolean {
        Log.i ("CollectorRepositoryImpl", "Almacenando ${collector}")
        return CollectorServiceAdapter.saveCollector(collector)
    }

}
