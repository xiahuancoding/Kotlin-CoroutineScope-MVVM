package com.example.reviewmycp.net

import com.example.reviewmycp.model.BaseResult
import com.example.reviewmycp.model.LoginModel
import com.example.reviewmycp.model.SendCodeModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*


/**
 * 返回的实体是ResponseBody的，会返回一个完整的json数据
 * 其他的实体都是给返回的json进行了封装的实体
 */
interface ApiService {


    @GET
    suspend fun get(@Url url: String?, @QueryMap params: WeakHashMap<String, Any>): BaseResult<String>


    @GET
    fun getString(@Url url: String?, @QueryMap params: WeakHashMap<String, Any>): Call<ResponseBody>

    @POST
    fun postString(@Url url:String, @Body body: RequestBody):Call<ResponseBody>


    @POST
    suspend fun login(@Url url:String, @Body body: RequestBody) : BaseResult<LoginModel>


    @GET
    suspend fun sendCode(@Url url: String, @QueryMap params: WeakHashMap<String, Any>): BaseResult<SendCodeModel>


    @Multipart
    @POST
    fun uploadImage(@Url url: String, @Part file: MultipartBody.Part?): Call<ResponseBody>

}