package com.example.reviewmycp.model

import com.chad.library.adapter.base.entity.MultiItemEntity

data class MineBottomModel(

    var itemType:Int? = null,
    var mUserAsKeeperInfo: UserAsKeeperInfo? = null, // 申请店主数据
    var mUserMoney:UserMoneyModel? = null, // 我的资产
    var orderList:MutableList<OrderAndService>? = null,
    var serviceList:MutableList<OrderAndService>? = null


) : MultiItemEntity {
    override fun getItemType(): Int = itemType!!
    companion object {
        const val MINE_ITEM_APPLY = 1 // 申请店主，申请卓越店主
        const val MINE_ITEM_FUND = 2  // 我的资产
        const val MINE_ITEM_SHOP = 3 // 我的店铺
        const val MINE_ITEM_ORDER = 4 // 我的订单
        const val MINE_ITEM_SERVICE = 5 // 我的服务


    }
}

data class OrderAndService(
    val icon:Int,
    val content:String,
    var messageNumber:String = "",
    val type:Int = 1 // 1我的订单 ， 2我的服务
)