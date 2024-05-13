package co.com.uniandes.vinilos.performer.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.com.uniandes.vinilos.performer.model.Performer
import co.com.uniandes.vinilos.performer.repository.adapters.PerformerServiceAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PerformerRepositoryImpl(private val context: Context) : PerformerRepository {

    private val serviceAdapter = PerformerServiceAdapter(context)
    val performersLiveData = MutableLiveData<List<Performer>>()

    override fun getPerformer(id: Int): LiveData<Performer?> {
        val liveData = MutableLiveData<Performer?>()
        val request = PerformerServiceAdapter.getPerformer(
            id,
            { response ->
                val gson = Gson()
                try {
                    val performer: Performer = gson.fromJson(response, Performer::class.java)
                    liveData.postValue(performer)
                    Log.e("PerformerRepositoryImpl", "Album fetched: ${performer}")
                } catch (e: Exception) {
                    Log.e("PerformerRepositoryImpl", "Error parsing album", e)
                }
            },
            { error ->
                Log.e("PerformerRepositoryImpl", "Error fetching performer: ${error.toString()}")
                liveData.postValue(null)
            }
        )
        serviceAdapter.instance.add(request)
        return liveData
    }

    override fun getPerformers(): LiveData<List<Performer>> {
        val performerListType = object : TypeToken<List<Performer>>() {}.type
        val request = PerformerServiceAdapter.getPerformers(
            { response ->
                val gson = Gson()
                val performers: List<Performer> = gson.fromJson(response, performerListType)
                performersLiveData.postValue(performers)
                Log.e("PerformerRepositoryImpl", "gson lo dejó como $performers")
            },
            { error ->
                Log.e("PerformerRepositoryImpl", "Error: ${error.toString()}")
                performersLiveData.postValue(emptyList())
            }
        )
        serviceAdapter.instance.add(request)
        return performersLiveData
    }


}
