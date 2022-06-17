package edu.fzu.mobius.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import java.io.*
import java.net.URL
import kotlin.Throws

class BitmapUtil {
    /**
     * todo 将网络资源图片转换为Bitmap
     * @param imgUrl 网络资源图片路径
     * @return Bitmap
     * 该方法调用时要放在子线程中
     */
    companion object{

        fun netToLocalBitmap(imgUrl: String?): Bitmap? {
            var bitmap: Bitmap? = null
            var `in`: InputStream? = null
            var out: BufferedOutputStream? = null
            return try {
                `in` = BufferedInputStream(URL(imgUrl).openStream(), 1024)
                val dataStream = ByteArrayOutputStream()
                out = BufferedOutputStream(dataStream, 1024)
                copy(`in`, out)
                out.flush()
                var data: ByteArray? = dataStream.toByteArray()
                bitmap = BitmapFactory.decodeByteArray(data, 0, data!!.size)
                data = null
                bitmap
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }

        @Throws(IOException::class)
        private fun copy(`in`: InputStream?, out: OutputStream) {
            val b = ByteArray(1024)
            var read: Int
            while (`in`!!.read(b).also { read = it } != -1) {
                out.write(b, 0, read)
            }
        }
    }
}