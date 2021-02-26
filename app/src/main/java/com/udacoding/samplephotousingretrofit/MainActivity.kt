package com.udacoding.samplephotousingretrofit

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.udacoding.samplephotousingretrofit.R
import com.udacoding.samplephotousingretrofit.databinding.ActivityMainBinding
import com.udacoding.samplephotousingretrofit.databinding.DialogChooseImageBinding
import com.udacoding.samplephotousingretrofit.network.Constan.Companion.code.CAMERA_CODE
import com.udacoding.samplephotousingretrofit.network.Constan.Companion.code.GALLERY_CODE
import com.udacoding.samplephotousingretrofit.network.NetworkModule
import com.udacoding.samplephotousingretrofit.utils.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.IOException
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var image_path: String? = null
    private var mime_type: String? = null
    lateinit var binding: ActivityMainBinding
    lateinit var dialog: BottomSheetDialog
    private var compositeDisposable = CompositeDisposable()
    lateinit var dialogChooseImageBinding: DialogChooseImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initPermission()

        initView()
    }

    private fun initView() {


        binding.chooseImg.setOnClickListener {
            showChooseImage()
        }
        binding.button.setOnClickListener {

            val name: RequestBody = binding.edName.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val type: RequestBody = binding.edJenis.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val sup: RequestBody = binding.edSup.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val stock: RequestBody = binding.edStock.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val price: RequestBody = binding.edPrice.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())


            val file = File(image_path)

            val requestFile: RequestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val body: MultipartBody.Part = MultipartBody.Part.createFormData("image", file.name, requestFile)

            compositeDisposable.add(NetworkModule.provideApiService().postData(name, type, sup, stock, price, body)
                    .doOnError {
                        Log.d("TAG", it.localizedMessage)
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                        Log.d("TAG SUCCESS", "$it.message")
                    }, {}))

        }

    }
    private fun showChooseImage() {
        dialogChooseImageBinding =
                DialogChooseImageBinding.bind(View.inflate(this, R.layout.dialog_choose_image, null))
        dialog = BottomSheetDialog(this).apply {
            setContentView(dialogChooseImageBinding.root)
            show()
        }
        dialogChooseImageBinding.imageViewCamera.setOnClickListener {
            openCamera()
            dialog.dismiss()
        }

        dialogChooseImageBinding.imageViewGallery.setOnClickListener {
            openGallery()
            dialog.dismiss()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_CODE && resultCode == Activity.RESULT_OK) {
            initCamera(data)
        } else if (requestCode == GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            initGallery(data)
        }
    }

    private fun initGallery(data: Intent?) {
        val image_bitmap = onSelectFromGalleryResult(data)
        binding.imageView.setImageBitmap(image_bitmap)
    }

    private fun initCamera(data: Intent?) {
        try {
            val image = data?.extras?.get("data")
            val random = Random.nextInt(0, 999999)
            var name_file ="PurchaseImage$random"


            image_path = persistImage(image as Bitmap, name_file)


            Log.d("TAG", "initCamera: MimeType : $mime_type")

            binding.imageView.setImageBitmap(BitmapFactory.decodeFile(image_path))

        } catch (e: Exception) {
            Log.d("Error", "initCameraException: $e")
        }
    }

    private fun onSelectFromGalleryResult(data: Intent?): Bitmap {
        var bm: Bitmap? = null

        Log.d("TAG", "onSelectFromGalleryResult: Masuk kesini")
        if (data != null) {
            Log.d("TAG", "onSelectFromGalleryResult: Sini juga Masuk")
            try {



                image_path = data.data?.let { FilePath.getPath(this, it) }

                Log.d("gallery_path", image_path ?: "")

                bm =
                        MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, data.data)

            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return bm!!
    }
}