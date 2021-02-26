package com.udacoding.samplephotousingretrofit.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.udacoding.samplephotousingretrofit.R
import com.udacoding.samplephotousingretrofit.network.Constan.Companion.code.CAMERA_CODE
import com.udacoding.samplephotousingretrofit.network.Constan.Companion.code.GALLERY_CODE
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.time.Duration

fun Context.showAlert(title: String, message: String, cancel: Boolean) {
    AlertDialog.Builder(this).apply {
        setTitle(title)
        setMessage(message)
        setCancelable(cancel)
        setPositiveButton("OK") { _, _ ->

        }.show()
    }
}



fun View.showSnackbar(message: String, duration: Int) {
    Snackbar.make(this, message, duration).show()
}

fun Activity.moveActivity(destination: Class<out Activity>) {
    startActivity(Intent(this, destination))
    finish()
}

fun Activity.openActivity(destination: Class<out Activity>) {
    startActivity(Intent(this, destination))
}

fun Activity.initPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(
            arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_NETWORK_STATE
            ), 1
        )
    }
}

fun Activity.initPermissionLocation() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ), 2
        )
    }
}

fun Activity.initPermissionBluetooth() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(
            arrayOf(
                android.Manifest.permission.BLUETOOTH,
                android.Manifest.permission.BLUETOOTH_ADMIN
            ), 3
        )
    }
}

fun Activity.openCamera() {
    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    startActivityForResult(cameraIntent, CAMERA_CODE)
}

fun Activity.openGallery() {
    val mimeTypes = arrayOf("image/jpg", "image/jpeg", "image/gif")

    val intent = Intent()
    intent.type = "*/*"
    intent.action = Intent.ACTION_GET_CONTENT
    intent.addCategory(Intent.CATEGORY_OPENABLE)
    intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

    startActivityForResult(
        Intent.createChooser(intent, getString(R.string.choose_image)),
        GALLERY_CODE
    )
}

fun Activity.persistImage(bitmap: Bitmap, name: String): String {
    val filesDir = filesDir
    val imageFile = File(filesDir, name + ".png")

    val image_path = imageFile.path

    val os: OutputStream?
    try {
        os = FileOutputStream(imageFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os)
        os.flush()
        os.close()
    } catch (e: Exception) {
        Log.e(javaClass.simpleName, getString(R.string.error_writing_bitmap), e)
    }

    return image_path ?: ""
}

fun Activity.getMimeTypeFile(uri: Uri?): String {
    var mimeType = ""
    if (uri?.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
        val cr = getContentResolver()
        mimeType = cr.getType(uri!!)!!
    } else {
        val fileExtension = MimeTypeMap.getFileExtensionFromUrl(
            uri
                .toString()
        )
        mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
            fileExtension.toLowerCase()
        )!!
    }
    return mimeType
}

fun RelativeLayout.show() {
    visibility = View.VISIBLE
}

fun RelativeLayout.hide() {
    visibility = View.INVISIBLE
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun LinearLayout.show() {
    visibility = View.VISIBLE
}

fun LinearLayout.hide() {
    visibility = View.GONE
}

fun Button.show() {
    visibility = View.VISIBLE
}

fun Button.hide() {
    visibility = View.GONE
}

fun CardView.show() {
    visibility = View.VISIBLE
}

fun CardView.hide() {
    visibility = View.GONE
}

fun ImageView.show() {
    visibility = View.VISIBLE
}

fun ImageView.hide() {
    visibility = View.INVISIBLE
}

fun CheckBox.show() {
    visibility = View.VISIBLE
}

fun CheckBox.hide() {
    visibility = View.INVISIBLE
}

fun RecyclerView.show() {
    visibility = View.VISIBLE
}

fun RecyclerView.hide() {
    visibility = View.INVISIBLE
}

/*fun Context.myToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}*/

