package com.udacoding.samplephotousingretrofit.model

import com.google.gson.annotations.SerializedName

data class UploadPhoto(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)
