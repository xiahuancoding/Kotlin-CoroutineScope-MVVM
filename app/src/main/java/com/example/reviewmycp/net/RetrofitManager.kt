package com.example.reviewmycp.net

import com.example.reviewmycp.net.intercept.Level
import com.example.reviewmycp.net.intercept.LoggingInterceptor
import com.example.reviewmycp.utlis.ConfigKeys
import com.example.reviewmycp.utlis.Latte
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import rx.android.BuildConfig
import java.util.concurrent.TimeUnit


class RetrofitManager {



    companion object {
        fun getInstance() = SingletonHolder.INSTANCE
        private lateinit var retrofit:Retrofit

    }

    private object SingletonHolder {
        val INSTANCE = RetrofitManager()
    }


    init {
        val baseUrl: String = Latte.getConfiguration(ConfigKeys.API_HOST)

        retrofit = Retrofit.Builder()
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }


    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20L, TimeUnit.SECONDS)
            .addInterceptor(TokenInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .writeTimeout(20L, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(8, 15, TimeUnit.SECONDS))
            .build()
    }

    fun <T> create(apiService:Class<T>) : T = retrofit.create(apiService) ?: throw RuntimeException("Api service is null")
}