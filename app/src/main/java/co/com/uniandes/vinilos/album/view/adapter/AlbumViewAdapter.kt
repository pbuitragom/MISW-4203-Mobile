package co.com.uniandes.vinilos.album.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.AlbumListener
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.album.model.Album
import com.bumptech.glide.Glide
import java.util.Locale


class AlbumViewAdapter(
    private val context: Context, private val albums: MutableList<Album>, private val listener: AlbumListener
) :
    RecyclerView.Adapter<AlbumViewAdapter.AlbumViewHolder>(){

    private var albumsFiltered: MutableList<Album> = albums

    class AlbumViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_album, parent, false)) {
        private var imageView: ImageView? = null
        private var titleTextView: TextView? = null
        private var detailsTextView: TextView? = null

        init {
            imageView = itemView.findViewById(R.id.album_cover_image)
            titleTextView = itemView.findViewById(R.id.album_title_text)
            detailsTextView = itemView.findViewById(R.id.album_details_text)
        }

        fun bind(album: Album, listener: AlbumListener) {
            titleTextView?.text = album.name
            detailsTextView?.text =
                album.genre
            imageView?.let {
                Glide.with(itemView.context)
                    .load(album.cover)
                    //.placeholder(R.drawable.ic_album_placeholder) // Reemplazar con una imagen de placeholder
                    .into(it)
            }
            itemView.setOnClickListener {
                Log.e("AlbumViewAdapter", "El album en el adaptador es  ${album} y tiene id ${album.albumId}")
                listener.openDetailAlbum(album.albumId)
            }
        }
    }

    fun updateData(newAlbums: List<Album>) {
        this.albums.clear() // Limpiamos la lista existente
        this.albums.addAll(newAlbums) // Añadimos todos los nuevos álbumes
        this.albumsFiltered = albums.toMutableList() // Actualizamos la lista filtrada
        notifyDataSetChanged() // Notificamos al adaptador del cambio
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album: Album = albumsFiltered[position]

        holder.bind(album, listener)
    }

    override fun getItemCount(): Int = albumsFiltered.size

    fun filter(query: String) {
        val queryString = query.lowercase(Locale.getDefault())
        albumsFiltered = if (queryString.isEmpty()) {
            albums
        } else {
            albums.filter {
                it.name.lowercase(Locale.getDefault()).contains(queryString)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }


}
