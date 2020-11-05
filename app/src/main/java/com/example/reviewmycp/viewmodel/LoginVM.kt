package com.example.reviewmycp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.reviewmycp.model.LoginModel
import com.example.reviewmycp.model.SendCodeModel
import com.example.reviewmycp.net.HttpConstant
import com.example.reviewmycp.utlis.RepositoryUtils
import com.example.reviewmycp.utlis.SmsType
import java.util.*

class LoginVM : BaseViewModel(){

    val loginRepo by lazy { RepositoryUtils.getLoginRepository() }

    var phone:String? = ""
    var pwd:String? = ""
    var code:String? = ""
    var loginType:Int = LOGIN_CODE

    var loginData = MutableLiveData<LoginModel>()
    var loginSendCodeData = MutableLiveData<SendCodeModel>()

    fun loginByPwd(){
       val params = WeakHashMap<String,Any>()
        params["account"] = phone
        params["password"] = pwd

        launchOnlyResult({loginRepo.login(HttpConstant.USER_LOGIN_PWD,params)},{
            loginData.postValue(it)
        })
    }


    fun loginByVerifyCode(){
        val params = WeakHashMap<String,Any>()
        params["account"] = phone
        params["code"] = code

        launchOnlyResult({loginRepo.login(HttpConstant.USER_LOGIN_CODE,params)},{
            loginData.postValue(it)
        })
    }


    fun sendCode(){
        val params = WeakHashMap<String,Any>()
        params["phone"] = phone
        params["type"] = SmsType.CODE_SIGN_IN

        launchOnlyResult({loginRepo.sendCode(HttpConstant.SEND_CODE,params)},{
            loginSendCodeData.postValue(it)
        })


    }






    companion object {
        const val LOGIN_PWD = 1
        const val LOGIN_CODE = 2


    }

}