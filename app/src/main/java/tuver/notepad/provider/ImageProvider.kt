package tuver.notepad.provider

import android.widget.ImageView

interface ImageProvider {

    fun loadImage(url: String, imageView: ImageView)

}