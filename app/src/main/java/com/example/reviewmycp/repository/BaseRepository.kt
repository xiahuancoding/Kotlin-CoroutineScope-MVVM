package com.example.reviewmycp.repository

import com.alibaba.fastjson.JSON
import com.example.reviewmycp.net.ApiService
import com.example.reviewmycp.net.RetrofitManager
import okhttp3.MediaType
import okhttp3.RequestBody
import java.util.*


/** 处理一些网络请求公共方法，比如转换参数
 * 具体的get，post请求由其子类实现
 */
open class BaseRepository {


    val mService = RetrofitManager.getInstance().create(ApiService::class.java)


    fun params2Body(params: WeakHashMap<String, Any>): RequestBody {
        val json = JSON.toJSONString(params)
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json)
    }




    companion object {

        @Volatile
        private var INSTANCE: BaseRepository? = null

        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: BaseRepository().also { INSTANCE = it }
            }
    }


}