package com.example.reviewmycp.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.reviewmycp.R
import com.example.reviewmycp.model.OrderAndService

class MineBottomOrderAdapter(layout:Int) : BaseQuickAdapter<OrderAndService,BaseViewHolder>(layout){


    override fun convert(helper: BaseViewHolder, item: OrderAndService) {
          helper.setImageResource(R.id.icon,item.icon)
          helper.setText(R.id.tvName,item.content)
    }

}