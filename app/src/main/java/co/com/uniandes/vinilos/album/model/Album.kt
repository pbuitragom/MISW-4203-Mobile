package co.com.uniandes.vinilos.album.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Album (
    @SerializedName("id")
    val albumId:Int,
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String
): Serializable