package co.com.uniandes.vinilos.performer.repository.adapters

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class PerformerServiceAdapter constructor(context: Context) {
    val instance: RequestQueue = Volley.newRequestQueue(context.applicationContext)

    companion object{

        const val BASE_URL= "http://34.168.191.6/"
        const val RESOURCE= "musicians"

        fun getPerformers(responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
            return StringRequest(Request.Method.GET, BASE_URL+RESOURCE, responseListener,errorListener)
        }
        fun createPerformer(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
            return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
        }
        fun getPerformer(albumId: Int, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
            return StringRequest(Request.Method.GET,
                "$BASE_URL$RESOURCE/$albumId", responseListener,errorListener)
        }

    }
}