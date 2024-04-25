package co.com.uniandes.vinilos.album.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.album.viewModels.AlbumViewModel2



class AlbumActivity : AppCompatActivity() {


    /*private val viewModel: AlbumViewModel2 by viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAlbums)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AlbumAdapter2()
        findViewById<RecyclerView>(R.id.recyclerViewAlbums).apply {
            layoutManager = LinearLayoutManager(this@AlbumActivity)
            this.adapter = adapter
        }

        viewModel.albums.observe(this) { albums ->
            adapter.submitList(albums)
        }

        viewModel.fetchAlbums()
    }*/
}
