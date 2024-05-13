package co.com.uniandes.vinilos.performer.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Performer (
    @SerializedName("id")
    val id:Int,
    val name:String,
    val image:String,
    val description:String,
    val birthDate:String,
): Serializable