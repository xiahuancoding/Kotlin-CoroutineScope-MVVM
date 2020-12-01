package com.example.reviewmycp.adapter

import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.reviewmycp.R
import com.example.reviewmycp.model.OrderAndService
import q.rorbin.badgeview.Badge
import q.rorbin.badgeview.QBadgeView

class MineBottomOrderAdapter(layout:Int) : BaseQuickAdapter<OrderAndService,BaseViewHolder>(layout){


    override fun convert(helper: BaseViewHolder, item: OrderAndService) {
          helper.setImageResource(R.id.icon,item.icon)
          helper.setText(R.id.tvName,item.content)
          if (item.type == 1)
          addBadgeAt(item.messageNumber.toInt(),helper.itemView.findViewById(R.id.root))
    }



    private fun addBadgeAt(number: Int,view: View): Badge? { // add badge
        return QBadgeView(mContext)
            .setBadgeTextSize(10f, true)
            .setBadgeNumber(number)
            .setGravityOffset(10f, 0f, true)
            .bindTarget(view)
    }
}