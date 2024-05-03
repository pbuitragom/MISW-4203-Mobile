package co.com.uniandes.vinilos.performer.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.PerformerListener
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.performer.model.Performer
import com.bumptech.glide.Glide
import java.util.Locale


class PerformerViewAdapter(
    private val context: Context, private val performers: MutableList<Performer>, private val listener: PerformerListener
) :
    RecyclerView.Adapter<PerformerViewAdapter.PerformerViewHolder>(){

    private var performersFiltered: MutableList<Performer> = performers

    class PerformerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_performer, parent, false)) {
        private var imageView: ImageView? = null
        private var titleTextView: TextView? = null
        private var detailsTextView: TextView? = null

        init {
            imageView = itemView.findViewById(R.id.album_cover_image)
            titleTextView = itemView.findViewById(R.id.album_title_text)
            detailsTextView = itemView.findViewById(R.id.album_details_text)
        }

        fun bind(performer: Performer, listener: PerformerListener) {
            titleTextView?.text = performer.name
            detailsTextView?.text =
                performer.description
            imageView?.let {
                Glide.with(itemView.context)
                    .load(performer.image)
                    //.placeholder(R.drawable.ic_album_placeholder) // Reemplazar con una imagen de placeholder
                    .into(it)
            }
            itemView.setOnClickListener {
                Log.e("PerformerViewAdapter", "El performer en el adaptador es  id ${performer.id}")
                listener.openDetailPerformer(performer.id)
            }
        }
    }

    fun updateData(newPerformers: List<Performer>) {
        this.performers.clear() // Limpiamos la lista existente
        this.performers.addAll(newPerformers) // Añadimos todos los nuevos álbumes
        this.performersFiltered = performers.toMutableList() // Actualizamos la lista filtrada
        notifyDataSetChanged() // Notificamos al adaptador del cambio
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerformerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PerformerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PerformerViewHolder, position: Int) {
        val performer: Performer = performersFiltered[position]

        holder.bind(performer, listener)
    }

    override fun getItemCount(): Int = performersFiltered.size

    fun filter(query: String) {
        val queryString = query.lowercase(Locale.getDefault())
        performersFiltered = if (queryString.isEmpty()) {
            performers
        } else {
            performers.filter {
                it.name.lowercase(Locale.getDefault()).contains(queryString)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }


}
