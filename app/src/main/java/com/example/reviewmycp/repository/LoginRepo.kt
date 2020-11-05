package com.example.reviewmycp.repository

import com.example.reviewmycp.model.BaseResult
import com.example.reviewmycp.model.LoginModel
import com.example.reviewmycp.model.SendCodeModel
import com.example.reviewmycp.net.IBaseResponse
import okhttp3.ResponseBody
import retrofit2.Call
import java.util.*

class LoginRepo : BaseRepository(){


    suspend fun login(url:String, params : WeakHashMap<String, Any>) : BaseResult<LoginModel> {
        return mService.login(url,params2Body(params))
    }

    suspend fun sendCode(url:String, params : WeakHashMap<String, Any>): BaseResult<SendCodeModel>{
        return mService.sendCode(url,params)
    }



    companion object {

        @Volatile
        private var INSTANCE: LoginRepo? = null

        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LoginRepo().also { INSTANCE = it }
            }
    }

}