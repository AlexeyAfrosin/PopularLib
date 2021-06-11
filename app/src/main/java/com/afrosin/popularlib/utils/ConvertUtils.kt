package com.afrosin.popularlib.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.*

fun Bitmap.compress(
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    quality: Int = 100
): ByteArrayOutputStream {

    val stream = ByteArrayOutputStream()
    this.compress(
        format,
        quality,
        stream
    )
    return stream
}

fun readFileToStream(fileName: String): InputStream {
    return BufferedInputStream(FileInputStream(fileName))
}

fun streamToBitmap(inputStream: InputStream): Bitmap? {
    return try {
        BitmapFactory.decodeStream(inputStream)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

fun Bitmap.toPng(): ByteArrayOutputStream {
    return this.compress(Bitmap.CompressFormat.PNG)
}


fun saveToFile(stream: ByteArrayOutputStream, path: String) {
    File(path).writeBytes(stream.toByteArray())
}