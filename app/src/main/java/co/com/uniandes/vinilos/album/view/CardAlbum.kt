package co.com.uniandes.vinilos.album.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import co.com.uniandes.vinilos.R

class CardAlbum @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_album_card, this, true)
    }

    // Añade métodos para configurar los datos del álbum
}
