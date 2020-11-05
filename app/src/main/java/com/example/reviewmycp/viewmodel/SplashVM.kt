package com.example.reviewmycp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.reviewmycp.net.HttpConstant
import com.example.reviewmycp.utlis.Constant
import com.example.reviewmycp.utlis.RepositoryUtils
import io.reactivex.disposables.Disposable
import java.util.*


class SplashVM : BaseViewModel(){

    private val splashRepo by lazy { RepositoryUtils.getSplashRepository() }

    val isShowData = MutableLiveData<String>()
    var disposable: Disposable? = null


    fun isShowOrDismissRequest(){
        launchOnlyResult({ splashRepo.getIsShowData(HttpConstant.SPLASH_URL,WeakHashMap())}, {
            isShowData.value = it
        },isShowDialog = false)

    }

}