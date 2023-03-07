package tuver.notepad.provider.impl

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import io.mockk.*
import org.junit.Test

class GlideImageProviderTest {

    private val mockGlideRequestManager: RequestManager = mockk()

    private val glideImageProvider = GlideImageProvider(mockGlideRequestManager)

    @Test
    fun `should load url into image view via request manager while loading an image`() {
        // given
        val url = "https://img.url/test.jpeg"
        val imageView: ImageView = mockk()
        val requestBuilder: RequestBuilder<Drawable> = mockk()
        every { mockGlideRequestManager.load(eq(url)) } returns requestBuilder
        every { requestBuilder.into(eq(imageView)) } returns mockk()

        // when
        glideImageProvider.loadImage(url, imageView)

        // then
        verifyAll {
            mockGlideRequestManager.load(eq(url))
            requestBuilder.into(eq(imageView))
        }
        confirmVerified(mockGlideRequestManager)
    }

    @Test
    fun `should append https protocol if protocol is missing in the url while loading an image`() {
        val url = "img.url/test.jpeg"
        val expectedUrl = "https://$url"
        val imageView: ImageView = mockk()
        val requestBuilder: RequestBuilder<Drawable> = mockk()
        every { mockGlideRequestManager.load(eq(expectedUrl)) } returns requestBuilder
        every { requestBuilder.into(eq(imageView)) } returns mockk()

        // when
        glideImageProvider.loadImage(url, imageView)

        // then
        verifyAll {
            mockGlideRequestManager.load(eq(expectedUrl))
            requestBuilder.into(eq(imageView))
        }
        confirmVerified(mockGlideRequestManager)
    }

    @Test
    fun `should not load image if the url is invalid`() {
        // given
        val url = "invalid url"
        val imageView: ImageView = mockk()

        // when
        glideImageProvider.loadImage(url, imageView)

        // then
        confirmVerified(mockGlideRequestManager)
    }

}