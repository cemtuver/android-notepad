package tuver.notepad.provider.impl

import android.widget.ImageView
import androidx.core.util.PatternsCompat
import com.bumptech.glide.RequestManager
import tuver.notepad.provider.ImageProvider
import javax.inject.Inject

class GlideImageProvider @Inject constructor(private val glideRequestManager: RequestManager) : ImageProvider {

    override fun loadImage(url: String, imageView: ImageView) {
        if (!URL_PATTERN.matcher(url).matches()) return

        val actualUrl = when {
            url.startsWith(URL_PREFIX_HTTP) || url.startsWith(URL_PREFIX_HTTPS) -> url
            else -> "$URL_PREFIX_HTTPS$url"
        }

        glideRequestManager.load(actualUrl).into(imageView)
    }

    companion object {

        private const val URL_PREFIX_HTTP = "http://"
        private const val URL_PREFIX_HTTPS = "https://"
        private val URL_PATTERN = PatternsCompat.WEB_URL

    }

}