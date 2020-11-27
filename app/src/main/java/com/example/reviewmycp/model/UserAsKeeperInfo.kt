package com.example.reviewmycp.model

import com.google.gson.annotations.SerializedName


/**
 * Created by wenbo, 2020/6/15
 */
data class UserAsKeeperInfo(

    // 申请店主字段
    @SerializedName("is_notify")
    val isNotify: Int,
    @SerializedName("is_store")
    val isStore: Int,
    @SerializedName("status")
    val status: Int,
    @SerializedName("status_label")
    val statusLabel: String,

    // 新增申请卓越店主的字段
    @SerializedName("is_gold")
    val is_gold:Int,
    @SerializedName("is_gold_notify")
    val is_gold_notify:Int,
    @SerializedName("gold_status")
    val gold_status:Int,
    @SerializedName("gold_status_label")
    val gold_status_label:String,

    // 新增降级通知字段
    @SerializedName("is_down_notify")
    val is_down_notify:Int,  // 优秀店主降级
    @SerializedName("is_gold_down_notify")
    val is_gold_down_notify:Int // 卓越店主降级

){

    fun isShopKeeper() = isStore == 1

    fun showNotifyDialog() = isNotify == 0 && isShopKeeper()

    fun isGold() = is_gold == 1;

}