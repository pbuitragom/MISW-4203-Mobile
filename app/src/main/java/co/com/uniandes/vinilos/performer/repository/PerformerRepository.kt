package co.com.uniandes.vinilos.performer.repository

import androidx.lifecycle.LiveData
import co.com.uniandes.vinilos.performer.model.Performer

interface PerformerRepository{

    fun getPerformer(albumId: Int): LiveData<Performer?>
    fun getPerformers():  LiveData<List<Performer>>
}
