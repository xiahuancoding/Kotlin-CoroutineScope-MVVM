package com.example.reviewmycp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.reviewmycp.utlis.RepositoryUtils
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class PersonInfoVM :BaseViewModel(){

    val personRepo by lazy { RepositoryUtils.getBaseRepository() }


    var picture = ""


    val AVATAR_OPTION = RequestOptions()
        .centerCrop()
        .transform(CircleCrop())
        .dontAnimate()



    fun createImageBody(): MultipartBody.Part{
        if (picture.isEmpty()){
            throw IllegalAccessException("图片路径不对")
        }

        val file = File(picture)
        val requestBody: RequestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), file)
        return MultipartBody.Part.createFormData("avatar", file.name, requestBody)

    }


}