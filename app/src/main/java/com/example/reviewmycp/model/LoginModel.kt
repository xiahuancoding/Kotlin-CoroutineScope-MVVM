package com.example.reviewmycp.model

data class LoginModel(

    val member:MemberBean,
    val memberProfile:MemberProfileBean


)


data class MemberBean(
    val id:String,
    val type:Int,
    val username:String,
    val phone:String,
    val balance:String,
    val level:Int,
    val accessToken:String


)



data class MemberProfileBean(
    val id:String,
    val nickname:String,
    val realname:String,
    val idcard:String,
    val avatar:String,
    val gender:Int


)