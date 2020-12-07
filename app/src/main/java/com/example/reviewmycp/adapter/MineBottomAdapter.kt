package com.example.reviewmycp.adapter

import android.content.Intent
import android.graphics.Typeface
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.reviewmycp.R
import com.example.reviewmycp.model.MineBottomModel
import com.example.reviewmycp.model.OrderAndService
import java.math.RoundingMode
import java.text.DecimalFormat


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
        helper.setGone(R.id.iv_arrow,false)

        val recycle = helper.getView<RecyclerView>(R.id.recycle)
        recycle.layoutManager = GridLayoutManager(mContext,4)
        val adapter = MineBottomOrderAdapter(R.layout.item_person_4_service)
        recycle.adapter = adapter

        val couponItem = OrderAndService(icon = R.mipmap.icon_sale_couns,content = "优惠券",type = 2)
        val addressItem = OrderAndService(icon = R.mipmap.icon_address_new,content = "地址",type = 2)
        val footer = OrderAndService(icon = R.mipmap.icon_footers,content = "足迹",type = 2)
        val aboutUs = OrderAndService(icon = R.mipmap.icon_abouts_us,content = "关于我们",type = 2)

        val dataList = mutableListOf<OrderAndService>()
        dataList.add(couponItem)
        dataList.add(addressItem)
        dataList.add(footer)
        dataList.add(aboutUs)
        adapter.setNewData(dataList)
    }

    private fun setApplyData(helper: BaseViewHolder, item: MineBottomModel) {
        val tvUpdateShopManagerBtn: AppCompatTextView = helper.getView(R.id.tv_update_shop_manager_btn)
        val ivApplyBrilliantShop: AppCompatImageView = helper.getView(R.id.iv_apply_brilliant_shop)

        //设置店主申请区域的 UI,显影跟mUserAsKeeperInfo对象有关，这个对象为null，直接隐藏
        if (item.mUserAsKeeperInfo != null) { // 店主是否显示
            if (!item.mUserAsKeeperInfo?.isShopKeeper()!!) {
                helper.setGone(R.id.apply_shopkeeper, true)
                LogUtils.d("showApply 店主= ")
//                isMineMoneyLayoutOne = true
                if (item.mUserAsKeeperInfo?.status == 1) {
                    helper.setText(R.id.tv_update_shop_manager_btn, "立即申请")
                } else if (item.mUserAsKeeperInfo?.status == 2 || item.mUserAsKeeperInfo?.status == 3) {
                    helper.setText(R.id.tv_update_shop_manager_btn, "查看进度")
                }
                tvUpdateShopManagerBtn.setOnClickListener {
//                    gotoShopkeeperApplingPage()
                }

            } else {
                helper.setGone(R.id.apply_shopkeeper, false)
//                isMineMoneyLayoutOne = false
            }
        }


        // 手动配置显示 && 不是卓越店主 && 是店主
        if (item.mUserAsKeeperInfo != null) {
            if (!item.mUserAsKeeperInfo?.isGold()!! && item.mUserAsKeeperInfo?.isShopKeeper()!!) {
                helper.setGone(R.id.cl_apply_brilliant_shop_mg, true)
                LogUtils.d("showApply 卓越店主= ")
//                isMineMoneyLayoutTwo = true
                // 申请卓越店主的按钮变化,和跳转
                helper.setImageResource(R.id.iv_apply_brilliant_shop,
                    if (item.mUserAsKeeperInfo?.gold_status == 1) R.mipmap.icon_apply_brilliant_btn else R.mipmap.icon_apply_brilliant_progress_btn
                )
                ivApplyBrilliantShop.setOnClickListener(View.OnClickListener { view: View? ->
                    if (item.mUserAsKeeperInfo?.gold_status == 1) { // 申请卓越店主
//                        mContext.startActivity(Intent(mContext, ApplyBrilliantShopManangerActivity::class.java))
                    } else { // 查看进度
//                        mContext.startActivity(Intent(mContext,ApplyBrilliantProgressActivity::class.java))
                    }
                })
            } else {
                helper.setGone(R.id.cl_apply_brilliant_shop_mg, false)
//                isMineMoneyLayoutTwo = false
            }
        }


    }

    private fun setFundData(helper: BaseViewHolder, item: MineBottomModel) {
        // 字体类型
        val typeFace = Typeface.createFromAsset(mContext.assets, "fonts/DIN-Medium.otf")
        helper.setTypeface(R.id.tv_all_total_money,typeFace)
        helper.setTypeface(R.id.tv_remain_money,typeFace)
        helper.setTypeface(R.id.tv_lively_value,typeFace)

        val format = DecimalFormat("0.##")
        val totalMoney = format.format(item.mUserMoney?.totalProfit?.toBigDecimal()?.setScale(2,RoundingMode.HALF_DOWN))
        val remainMoney = format.format(item.mUserMoney?.balance?.toBigDecimal()?.setScale(2,RoundingMode.HALF_DOWN))
        val sunshineValue = format.format(item.mUserMoney?.usableActiveDot?.toBigDecimal()?.setScale(2,RoundingMode.HALF_DOWN))
        helper.setText(R.id.tv_all_total_money,totalMoney)
        helper.setText(R.id.tv_remain_money,remainMoney)
        helper.setText(R.id.tv_lively_value,sunshineValue)
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

        val waitPayItem = OrderAndService(icon = R.mipmap.icon_wait_pay,content = "待付款"
            ,messageNumber = item.mUserMoney?.order_data?.wait_pay_num!!)
        val waitSendItem = OrderAndService(icon = R.mipmap.icon_wait_send_goods,content = "待发货",
            messageNumber = item.mUserMoney?.order_data?.wait_rec_num!!)
        val waitCollectItem = OrderAndService(icon = R.mipmap.icon_mine_wait_colllect_goods,content = "待收货",
            messageNumber = item.mUserMoney?.order_data?.wait_eva_num!!)
        val waitSaleItem = OrderAndService(icon = R.mipmap.icon_mine_sales_services,content = "售后说明",
            messageNumber = item.mUserMoney?.order_data?.wait_sales_num!!)

        val dataList = mutableListOf<OrderAndService>()
        dataList.add(waitPayItem)
        dataList.add(waitSendItem)
        dataList.add(waitCollectItem)
        dataList.add(waitSaleItem)
        adapter.setNewData(dataList)
    }





}