package com.example.reviewmycp.utlis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
import java.lang.reflect.ParameterizedType

/**
 *   @auther : Aleyn
 *   time   : 2019/11/01
 */
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.newInstance()
    }
}