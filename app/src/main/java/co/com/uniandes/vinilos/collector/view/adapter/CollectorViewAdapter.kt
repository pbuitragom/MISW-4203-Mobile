package co.com.uniandes.vinilos.collector.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.com.uniandes.vinilos.CollectorListener
import co.com.uniandes.vinilos.R
import co.com.uniandes.vinilos.collector.model.Collector
import com.bumptech.glide.Glide
import java.util.Locale


class CollectorViewAdapter(
    private val context: Context, private val collectors: MutableList<Collector>, private val listener: CollectorListener
) :
    RecyclerView.Adapter<CollectorViewAdapter.CollectorViewHolder>(){

    private var collectorsFiltered: MutableList<Collector> = collectors

    class CollectorViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_collector, parent, false)) {
        private var imageView: ImageView? = null
        private var titleTextView: TextView? = null
        private var detailsTextView: TextView? = null

        init {
            imageView = itemView.findViewById(R.id.album_cover_image)
            titleTextView = itemView.findViewById(R.id.album_title_text)
            detailsTextView = itemView.findViewById(R.id.album_details_text)
        }

        fun bind(item: Collector, listener: CollectorListener) {
            titleTextView?.text = item.name
            detailsTextView?.text =
                item.email
            detailsTextView?.text =
                item.telephone
            itemView.setOnClickListener {
                Log.e("CollectorViewAdapter", "El album en el adaptador es  ${item} y tiene id ${item.id}")
                listener.openDetailCollector(item.id)
            }
        }
    }

    fun updateData(newCollectors: List<Collector>) {
        this.collectors.clear() // Limpiamos la lista existente
        this.collectors.addAll(newCollectors) // Añadimos todos los nuevos álbumes
        this.collectorsFiltered = collectors.toMutableList() // Actualizamos la lista filtrada
        notifyDataSetChanged() // Notificamos al adaptador del cambio
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CollectorViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        val album: Collector = collectorsFiltered[position]

        holder.bind(album, listener)
    }

    override fun getItemCount(): Int = collectorsFiltered.size

    fun filter(query: String) {
        val queryString = query.lowercase(Locale.getDefault())
        collectorsFiltered = if (queryString.isEmpty()) {
            collectors
        } else {
            collectors.filter {
                it.name.lowercase(Locale.getDefault()).contains(queryString)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }


}
