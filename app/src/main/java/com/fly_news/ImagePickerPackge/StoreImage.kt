package com.fly_news.ImagePickerPackge

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import android.util.Log
import com.fly_news.utilities.ImagePicker_View
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class StoreImage {
    companion object {
        private val IMAGE_DIRECTORY = "/flying_news"
        fun saveImage(context:Context,myBitmap: Bitmap): File {
            val f: File
            val bytes = ByteArrayOutputStream()
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val wallpaperDirectory = File(
                    (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
            // have the object build the directory structure, if needed.
            Log.d("fee", wallpaperDirectory.toString())
            if (!wallpaperDirectory.exists()) {

                wallpaperDirectory.mkdirs()
            }

            try {
                Log.d("heel", wallpaperDirectory.toString())
                f = File(wallpaperDirectory, ((Calendar.getInstance()
                        .getTimeInMillis()).toString() + ".jpg"))
                f.createNewFile()
                val fo = FileOutputStream(f)
                fo.write(bytes.toByteArray())
                MediaScannerConnection.scanFile(context,
                        arrayOf(f.getPath()),
                        arrayOf("image/jpeg"), null)
                fo.close()
                Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

                return f
            } catch (e1: IOException) {
                e1.printStackTrace()
            }

            return File("")
        }
    }


}