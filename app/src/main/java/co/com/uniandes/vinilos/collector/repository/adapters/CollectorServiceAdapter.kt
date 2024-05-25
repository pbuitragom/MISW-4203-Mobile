package co.com.uniandes.vinilos.collector.repository.adapters

import android.content.Context
import android.util.Log
import co.com.uniandes.vinilos.collector.model.Collector
import co.com.uniandes.vinilos.collector.model.toDTO
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.util.InternalAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import io.ktor.http.contentType

class CollectorServiceAdapter(context: Context) {


    companion object {
        private const val BASE_URL = "http://34.168.191.6/"
        private const val RESOURCE = "collectors"


        @OptIn(InternalAPI::class)
        suspend fun getCollectors(): String? {
            return withContext(Dispatchers.IO) {
                try {
                    val client = HttpClient(Android)
                    val response: HttpResponse = client.get {
                        url("$BASE_URL$RESOURCE")
                    }
                    if (response.status == HttpStatusCode.OK) {
                        response.readBytes().toString(Charsets.UTF_8)
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }
        }

        suspend fun getCollector(id: Int): String? {
            return withContext(Dispatchers.IO) {
                try {
                    val client = HttpClient(Android)
                    val response: HttpResponse = client.get {
                        url("$BASE_URL$RESOURCE/$id")
                    }
                    if (response.status == HttpStatusCode.OK) {
                        response.readBytes().toString(Charsets.UTF_8)
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }
        }

        suspend fun saveCollector(collector: Collector): Boolean {
            return withContext(Dispatchers.IO) {

                var json = Gson().toJson(collector.toDTO())
                Log.i("SaveCollector", "Json: ${json}")
                try {
                    val client = HttpClient(Android)
                    val response: HttpResponse = client.post {
                        url("$BASE_URL$RESOURCE")
                        contentType(ContentType.Application.Json)
                        setBody(json)
                    }
                    response.status == HttpStatusCode.Created
                    Log.i("SaveCollector", "Response: ${response}")
                    true
                } catch (e: Exception) {
                    Log.e("SaveCollector", "Error almacenando el collectoe ${e.message}")
                    false
                }
            }
        }

    }


}
