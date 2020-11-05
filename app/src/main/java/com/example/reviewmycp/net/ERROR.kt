package com.example.reviewmycp.net

/**
 *   @auther : Aleyn
 *   time   : 2019/08/12
 */
enum class ERROR(private val code: Int, private val err: String) {

    /**
     * 未知错误
     */
    UNKNOWN(1000, "未知错误"),
    /**
     * 解析错误
     */
    PARSE_ERROR(1001, "解析错误"),
    /**
     * 网络错误
     */
    NETWORD_ERROR(1002, "网络错误"),
    /**
     * 协议出错
     */
    HTTP_ERROR(1003, "协议出错"),

    /**
     * 证书出错
     */
    SSL_ERROR(1004, "证书出错"),

    /**
     * 连接超时
     */
    TIMEOUT_ERROR(1006, "连接超时"),

    /**
     * token过期
     */
    TOKEN_ERROR(403, "token过期"),

    /**
     * 服务器错误
     */
    SERVICE_ERROR(500,"服务器错误");


    fun getValue(): String {
        return err
    }

    fun getKey(): Int {
        return code
    }

}