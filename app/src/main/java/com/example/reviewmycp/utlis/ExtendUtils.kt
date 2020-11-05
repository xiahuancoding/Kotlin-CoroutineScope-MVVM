package com.example.reviewmycp.utlis

import java.util.regex.Pattern



fun isChinaPhone(phone:String):Boolean{
    val regExp = "^((19[0-9])|(16[0-9])|(13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$"
    val p = Pattern.compile(regExp)
    val m = p.matcher(phone)
    return m.matches()
}