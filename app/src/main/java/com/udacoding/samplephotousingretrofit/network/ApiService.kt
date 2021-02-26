package com.udacoding.samplephotousingretrofit.network

import com.udacoding.samplephotousingretrofit.model.UploadPhoto
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface ApiService {

//proses ambil gambar
    //--> pick image from gallery / camera
    //--> bitmap --> path
    // --> path --> file
    @Multipart
    @POST("insert.php")
    fun postData(@Part("nama") name : RequestBody,
    @Part("jenis") type : RequestBody,
    @Part("suplier") sup : RequestBody,
    @Part("jumlah") stock : RequestBody,
    @Part("harga") price : RequestBody,
    @Part file : MultipartBody.Part):Single<UploadPhoto>


    @FormUrlEncoded
    @POST("insert.php")
    fun postDataNoImag(@Field("nama") name : String,
                       @Field("jenis") type : RequestBody,
                       @Part("suplier") sup : RequestBody,
                       @Part("jumlah") stock : RequestBody,
                       @Part("harga") price : RequestBody):Single<UploadPhoto>
}