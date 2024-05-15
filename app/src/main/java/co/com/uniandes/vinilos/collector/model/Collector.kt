package co.com.uniandes.vinilos.collector.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Collector (
    @SerializedName("id")
    val id:Int,
    val name:String,
    val telephone:String,
    val email:String
): Serializable