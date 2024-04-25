package co.com.uniandes.vinilos

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.album.view.adapter.AlbumViewAdapter
import co.com.uniandes.vinilos.album.viewModels.AlbumViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: AlbumViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_albums)

        // Instanciación del ViewModel
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(application))[AlbumViewModel::class.java]

        //val getButton: Button = findViewById(R.id.fetch_button)
        //val getResultTextView : TextView = findViewById(R.id.get_result_text)

        //Campa de adaptación visual con RecyclreView
        val recyclerView = findViewById<RecyclerView>(R.id.album_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observar los cambios en los datos del ViewModel
        viewModel.albums.observe(this, Observer { albumList ->
            //getResultTextView.text = "Response is: ${albumList}"
            recyclerView.adapter = AlbumViewAdapter(this, albumList)
        })


        viewModel.eventNetworkError.observe(this, Observer { isNetworkError ->
            if (isNetworkError) {
                if (!viewModel.isNetworkErrorShown.value!!) {
                    //showErrorDialog()
                    //getResultTextView.text = "That didn't work!"
                    viewModel.onNetworkErrorShown()
                }
            }
        })
    }

    private fun showErrorDialog() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
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


