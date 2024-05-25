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
            private var image: ImageView? = null
            private var name: TextView? = null
            private var email: TextView? = null
            private var phone: TextView? = null

        init {
            image = itemView.findViewById(R.id.collector_image)
            name = itemView.findViewById(R.id.collector_name)
            email = itemView.findViewById(R.id.collector_email_text)
            phone = itemView.findViewById(R.id.collector_telephone_text)
        }

        fun bind(item: Collector, listener: CollectorListener) {
            name?.text = item.name
            email?.text =
                item.email
            phone?.text =
                item.telephone
            image?.let {
                Glide.with(itemView.context)
                    .load(R.drawable.ic_default_person)
                    .into(it)
            }
            itemView.setOnClickListener {
                Log.e("CollectorViewAdapter", "El coleccionista en el adaptador es  ${item} y tiene id ${item.id}")
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
