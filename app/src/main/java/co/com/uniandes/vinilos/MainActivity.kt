package co.com.uniandes.vinilos

import AlbumServiceAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import co.com.uniandes.vinilos.album.model.Album
import com.android.volley.Response
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    lateinit var albumAdapter: AlbumServiceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        albumAdapter = AlbumServiceAdapter(this.applicationContext)

        val getButton: Button = findViewById(R.id.fetch_button)
        val getResultTextView : TextView = findViewById(R.id.get_result_text)
        getButton.setOnClickListener {
            albumAdapter.instance.add (AlbumServiceAdapter.getAlbums(
                {
                    val gson = Gson()
                    val albumListType = object : TypeToken<List<Album>>() {}.type
                    val albums: List<Album> = gson.fromJson(it, albumListType)
                    getResultTextView.text = "Response is: ${it}"
                },
                Response.ErrorListener {
                    Log.d("TAG", it.toString())
                    getResultTextView.text = "That didn't work!"
                }
            ))
        }

        val postButton: Button = findViewById(R.id.post_button)
        val postResultTextView : TextView = findViewById(R.id.post_result_text)
        postButton.setOnClickListener {
            val mailTxt : TextInputEditText = findViewById(R.id.txt_post_mail)
            val nameTxt : TextInputEditText = findViewById(R.id.txt_post_name)
            val phoneTxt : TextInputEditText = findViewById(R.id.txt_post_phone)
            val postParams = mapOf<String, Any>(
                "name" to nameTxt.text.toString(),
                "telephone" to phoneTxt.text.toString(),
                "email" to mailTxt.text.toString()
            )

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        //supportActionBar!!.title = "Volley"
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Create an intent with a destination of the other Activity
                val intent = Intent(this, RetrofitActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


