package com.example.reviewmycp.model

import com.example.reviewmycp.net.IBaseResponse

/**
 *   @auther : Aleyn
 *   time   : 2019/11/01
 */
data class BaseResult<T>(
    val message: String,
    val code: Int,
    val data: T
) : IBaseResponse<T> {

    override fun code() = code

    override fun msg() = message

    override fun data() = data

    override fun isSuccess() = code == 0

}