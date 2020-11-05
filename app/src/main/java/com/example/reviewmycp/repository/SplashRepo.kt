package com.example.reviewmycp.repository

import com.example.reviewmycp.model.BaseResult
import com.example.reviewmycp.net.IBaseResponse
import java.util.*

class SplashRepo : BaseRepository(){


    suspend fun getIsShowData(url: String, params: WeakHashMap<String, Any>): BaseResult<String> {
        return mService.get(url, params = params)
    }


    companion object {

        @Volatile
        private var INSTANCE: SplashRepo? = null

        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: SplashRepo().also { INSTANCE = it }
            }
    }
}