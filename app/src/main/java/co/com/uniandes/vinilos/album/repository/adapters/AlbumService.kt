package co.com.uniandes.vinilos.album.repository.adapters

import co.com.uniandes.vinilos.album.model.Album
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://10.0.2.2:3000/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface AlbumService {
    @GET("album")
    fun getAlbum():
            Call<String>


    @GET("albums")
    suspend fun getAlbums(): List<Album>

}

object Retrofit2Api {
    val retrofitService : RetrofitApiService by lazy {
        retrofit.create(RetrofitApiService::class.java) }
}