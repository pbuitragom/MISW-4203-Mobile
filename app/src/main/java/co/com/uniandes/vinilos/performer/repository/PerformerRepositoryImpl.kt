package co.com.uniandes.vinilos.performer.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import co.com.uniandes.vinilos.performer.model.Performer
import co.com.uniandes.vinilos.performer.repository.adapters.PerformerServiceAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers

class PerformerRepositoryImpl(private val context: Context) : PerformerRepository {

    override fun getPerformer(id: Int): LiveData<Performer?> = liveData(Dispatchers.IO) {
        val response = PerformerServiceAdapter.getPerformer(id)
        val performer = response?.let {
            try {
                Gson().fromJson(it, Performer::class.java)
            } catch (e: Exception) {
                Log.e("PerformerRepositoryImpl", "Error parsing performer", e)
                null
            }
        }
        emit(performer)
    }

    override fun getPerformers(): LiveData<List<Performer>> = liveData(Dispatchers.IO) {
        val response = PerformerServiceAdapter.getPerformers()
        val gson = Gson()
        val performerListType = object : TypeToken<List<Performer>>() {}.type
        val performers: List<Performer> = response?.let {
            try {
                if (it.startsWith("[")) {
                    gson.fromJson(it, performerListType)
                } else {
                    val singlePerformer: Performer = gson.fromJson(it, Performer::class.java)
                    listOf(singlePerformer)
                }
            } catch (e: Exception) {
                Log.e("PerformerRepositoryImpl", "Error parsing performers", e)
                emptyList()
            }
        } ?: emptyList()
        emit(performers)
    }
}
