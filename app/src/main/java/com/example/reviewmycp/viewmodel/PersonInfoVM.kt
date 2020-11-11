package com.example.reviewmycp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.reviewmycp.utlis.RepositoryUtils

class PersonInfoVM :BaseViewModel(){

    val personRepo by lazy { RepositoryUtils.getBaseRepository() }


    val AVATAR_OPTION = RequestOptions()
        .centerCrop()
        .transform(CircleCrop())
        .dontAnimate()






}