package co.com.uniandes.vinilos.album.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.album.model.Album
import com.bumptech.glide.Glide


class AlbumViewAdapter(private val context: Context, private val albums: List<Album>) :
    RecyclerView.Adapter<AlbumViewAdapter.AlbumViewHolder>() {

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

        fun bind(album: Album) {
            titleTextView?.text = album.name
            detailsTextView?.text = album.genre  // Ajusta esto según cómo quieras mostrar los detalles
            imageView?.let {
                Glide.with(itemView.context)
                    .load(album.cover)
                    //.placeholder(R.drawable.ic_album_placeholder) // Reemplazar con una imagen de placeholder
                    .into(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album: Album = albums[position]
        holder.bind(album)
    }

    override fun getItemCount(): Int = albums.size
}
