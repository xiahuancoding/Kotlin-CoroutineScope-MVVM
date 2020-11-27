package com.example.reviewmycp.model

data class UserInfoModel(

    val member:UserMember,
    val memberProfile:UserMemberProfile


)


data class UserMember(
    val id:String,
    val type:Int,
    val username:String,
    val level:Int

)

data class UserMemberProfile(
    val avatar:String


)