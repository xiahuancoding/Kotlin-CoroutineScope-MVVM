package com.example.reviewmycp.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.reviewmycp.R
import com.example.reviewmycp.model.MineBottomModel
import com.example.reviewmycp.model.OrderAndService
import com.example.reviewmycp.model.UserAsKeeperInfo


class MineBottomAdapter(data: List<MineBottomModel>) : BaseMultiItemQuickAdapter<MineBottomModel, BaseViewHolder>(data) {

    init {
        addItemType(MineBottomModel.MINE_ITEM_APPLY, R.layout.item_mine_apply)
        addItemType(MineBottomModel.MINE_ITEM_FUND,R.layout.item_mine_fund)
        addItemType(MineBottomModel.MINE_ITEM_SHOP,R.layout.item_mine_shop)
        addItemType(MineBottomModel.MINE_ITEM_ORDER,R.layout.item_mine_order)
        addItemType(MineBottomModel.MINE_ITEM_SERVICE,R.layout.item_mine_service)
    }


    override fun convert(helper: BaseViewHolder, item: MineBottomModel) {
        when(item.getItemType()){
            MineBottomModel.MINE_ITEM_APPLY ->{
                setApplyData(helper,item)
            }
            MineBottomModel.MINE_ITEM_FUND ->{
                setFundData(helper,item)
            }
            MineBottomModel.MINE_ITEM_SHOP ->{
                setShopData(helper,item)
            }
            MineBottomModel.MINE_ITEM_ORDER ->{
               setOrderData(helper,item)
            }
            MineBottomModel.MINE_ITEM_SERVICE ->{
                setServiceData(helper,item)
            }
        }
    }

    private fun setServiceData(helper: BaseViewHolder, item: MineBottomModel) {
        helper.setText(R.id.tv_title,"我的服务")

        val recycle = helper.getView<RecyclerView>(R.id.recycle)
        recycle.layoutManager = GridLayoutManager(mContext,4)
        val adapter = MineBottomOrderAdapter(R.layout.item_person_4_service)
        recycle.adapter = adapter

        val couponItem = OrderAndService(icon = R.mipmap.icon_sale_couns,content = "优惠券")
        val addressItem = OrderAndService(icon = R.mipmap.icon_address_new,content = "地址")
        val footer = OrderAndService(icon = R.mipmap.icon_footers,content = "足迹")
        val aboutUs = OrderAndService(icon = R.mipmap.icon_abouts_us,content = "关于我们")

        val dataList = mutableListOf<OrderAndService>()
        dataList.add(couponItem)
        dataList.add(addressItem)
        dataList.add(footer)
        dataList.add(aboutUs)
        adapter.setNewData(dataList)
    }

    private fun setApplyData(helper: BaseViewHolder, item: MineBottomModel) {

    }

    private fun setFundData(helper: BaseViewHolder, item: MineBottomModel) {
        helper.setText(R.id.tv_all_total_money,item.mUserMoney?.totalProfit)
        helper.setText(R.id.tv_remain_money,item.mUserMoney?.balance)
        helper.setText(R.id.tv_lively_value,item.mUserMoney?.usableActiveDot.toString())

    }

    private fun setShopData(helper: BaseViewHolder, item: MineBottomModel) {

    }

    private fun setOrderData(helper: BaseViewHolder, item: MineBottomModel) {
        helper.setText(R.id.tv_title,"我的订单")
        helper.setText(R.id.tv_sub_title,"全部订单")

        val recycle = helper.getView<RecyclerView>(R.id.recycle)
        recycle.layoutManager = GridLayoutManager(mContext,4)
        val adapter = MineBottomOrderAdapter(R.layout.item_person_4_service)
        recycle.adapter = adapter

        val waitPayItem = OrderAndService(icon = R.mipmap.icon_wait_pay,content = "待付款")
        val waitSendItem = OrderAndService(icon = R.mipmap.icon_wait_send_goods,content = "待发货")
        val waitCollectItem = OrderAndService(icon = R.mipmap.icon_mine_wait_colllect_goods,content = "待收货")
        val waitSaleItem = OrderAndService(icon = R.mipmap.icon_mine_sales_services,content = "售后说明")

        val dataList = mutableListOf<OrderAndService>()
        dataList.add(waitPayItem)
        dataList.add(waitSendItem)
        dataList.add(waitCollectItem)
        dataList.add(waitSaleItem)
        adapter.setNewData(dataList)
    }





}