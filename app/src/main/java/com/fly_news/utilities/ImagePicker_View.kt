package com.fly_news.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AlertDialog
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.content.FileProvider




class ImagePicker_View {
    var context: Context
    var activity: Activity? = null

    constructor(context: Context, activity: Activity) {
        this.context = context
        this.activity = activity
        showPictureDialog();
    }

    companion object {
        val GALLERY = 1
        val CAMERA = 2
        private val IMAGE_DIRECTORY = "/flying_news"
    }


    private fun showPictureDialog() {
        this.context = context;
        val pictureDialog = AlertDialog.Builder(context)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        activity!!.startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        activity!!.startActivityForResult(intent, CAMERA)
        /* var values = ContentValues()
         values.put(MediaStore.Images.Media.TITLE, "New Picture")
         values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera")
         var imageUri = context.getContentResolver().insert(
                 MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
         val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
         intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
         activity!!.startActivityForResult(intent, CAMERA)*/
    }

    fun saveImage(myBitmap: Bitmap): File {
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