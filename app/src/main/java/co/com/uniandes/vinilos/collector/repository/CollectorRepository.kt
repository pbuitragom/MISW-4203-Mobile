package co.com.uniandes.vinilos.collector.repository

import androidx.lifecycle.LiveData
import co.com.uniandes.vinilos.collector.model.Collector

interface CollectorRepository{

    fun getCollector(collectorId: Int): LiveData<Collector?>
    fun getCollectors():  LiveData<List<Collector>>
    suspend fun save(collector: Collector): Boolean
}
