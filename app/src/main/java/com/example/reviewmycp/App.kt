package com.example.reviewmycp

import android.app.Application
import com.example.reviewmycp.utlis.Latte
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        initConfig()
    }

    private fun initConfig() {
        GlobalScope.launch {
            Latte.init(applicationContext)
                .withApiHost(BuildConfig.APP_HOST)
//                .withH5IM(BuildConfig.H5_IM)
//                .withUseId(0)
//                .withLoaderDelayed(500)
//                .withInterceptor(TokenInterceptor())
//                .withInterceptor(ResponesInterceptor())
//                .withInterceptor(interceptor)
//                .withImWs(BuildConfig.APP_IM_WS)
//                .withStatistic(BuildConfig.APP_STITSTIC_HOST)
//                .withImHost(BuildConfig.APP_IM_HOST)
//                .withLiveHost(BuildConfig.APP_LIVE_HOST)
//                .withWwxPay(BuildConfig.APP_WWX_PAY)
//                .withLiveIm(BuildConfig.APP_LIVE_IM)
//                .withWeChatAppId(BuildConfig.WX_APPID)
//                .withCImWs(BuildConfig.C_APP_IM_WS)
//                .withCImApp(BuildConfig.C_APP_IM_API)
//                .withDeBug(BuildConfig.IS_BETA)
//                .withWWXTYPE(BuildConfig.APP_WWX_PAY_TYPE)
                .configure()


        }
    }


}
