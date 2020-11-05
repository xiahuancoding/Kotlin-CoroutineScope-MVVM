package com.example.reviewmycp.net

import android.net.ParseException
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException


object ExceptionHandle {
    // 网络异常的处理
    fun handleException(e: ResponseThrowable): ResponseThrowable {
        if (e.code == 403){
            return ResponseThrowable(ERROR.TOKEN_ERROR, e)
        }else{
            // 服务端业务错误code码处理
            if(e.code >= 10000){
               return ResponseThrowable(e.code,e.errMsg,e)
            }

            // 网络服务器错误
            if (e.code in 500..9999){
                return ResponseThrowable(ERROR.SERVICE_ERROR, e)
            }
        }

        return ResponseThrowable(ERROR.UNKNOWN, e)
//        if (e is HttpException) {
//            ex = ResponseThrowable(ERROR.HTTP_ERROR, e)
//        } else if (e is JsonParseException
//            || e is JSONException
//            || e is ParseException || e is MalformedJsonException
//        ) {
//            ex = ResponseThrowable(ERROR.PARSE_ERROR, e)
//        } else if (e is ConnectException) {
//            ex = ResponseThrowable(ERROR.NETWORD_ERROR, e)
//        } else if (e is javax.net.ssl.SSLException) {
//            ex = ResponseThrowable(ERROR.SSL_ERROR, e)
//        } else if (e is java.net.SocketTimeoutException) {
//            ex = ResponseThrowable(ERROR.TIMEOUT_ERROR, e)
//        } else if (e is java.net.UnknownHostException) {
//            ex = ResponseThrowable(ERROR.TIMEOUT_ERROR, e)
//        } else {
//            ex = ResponseThrowable(ERROR.UNKNOWN, e)
//        }


    }
}