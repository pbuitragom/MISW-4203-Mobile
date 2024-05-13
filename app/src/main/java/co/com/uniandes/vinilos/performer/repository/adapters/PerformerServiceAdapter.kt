package co.com.uniandes.vinilos.performer.repository.adapters

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class PerformerServiceAdapter(context: Context) {

    companion object {
        private const val BASE_URL = "http://34.168.191.6/"
        private const val RESOURCE = "musicians"

        suspend fun getPerformers(): String? {
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

        suspend fun createPerformer(path: String, body: JSONObject): JSONObject? {
            return withContext(Dispatchers.IO) {
                try {
                    val client = HttpClient(Android)
                    val response: HttpResponse = client.post {
                        url("$BASE_URL$path")
                    }
                    if (response.status == HttpStatusCode.Created) {
                        JSONObject(response.readBytes().toString(Charsets.UTF_8))
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }
        }

        suspend fun getPerformer(id: Int): String? {
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
    }
}
