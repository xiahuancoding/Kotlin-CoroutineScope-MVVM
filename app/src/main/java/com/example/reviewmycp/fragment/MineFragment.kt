package com.example.reviewmycp.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.reviewmycp.R
import com.example.reviewmycp.adapter.MineBottomAdapter
import com.example.reviewmycp.model.MineBottomModel
import com.example.reviewmycp.model.UserAsKeeperInfo
import com.example.reviewmycp.model.UserInfoModel
import com.example.reviewmycp.model.UserMoneyModel
import com.example.reviewmycp.utlis.*
import com.example.reviewmycp.viewmodel.MineInfoVM
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseFragment<MineInfoVM>(),SwipeRefreshLayout.OnRefreshListener {


    override fun layoutId(): Int = R.layout.fragment_mine


    override fun initView(savedInstanceState: Bundle?) {
        initRecycleView()
        swipe_refresh.setOnRefreshListener(this)
    }

    override fun initData() {
        viewModel.requestUserInfo()
        viewModel.requestUserApplyShopManager()
        viewModel.requestUserInfoMoney()
        observerAllData()
    }

    private fun observerAllData() {
        viewModel.userInfoData.observe(this, Observer {
            // Log.d("xiecheng","携程请求的数据userInfoData-----------------------${it.member.username}")
            stopRefresh()
            setUserInfo(it)
        })
        viewModel.userApplyShopData.observe(this, Observer {
            Log.d("xiecheng", "携程请求的数据userApplyShopData-----------------------${it.isStore}")
            stopRefresh()
            if (it != null) {
                viewModel.applyItem.mUserAsKeeperInfo = it
                isShowApplyShopManagerNotifyWindow(it)
                viewModel.requestUserApplyShopManagerSwitch()
            }

        })
        viewModel.userApplyShopSwitchData.observe(this, Observer {
            Log.d("xiecheng", "携程请求的数据userApplyShopSwitchData-----------------------${it}")
            stopRefresh()
            if (it){
                viewModel.bottomData.add(viewModel.applyItem)
                viewModel.refreshBottomData()
            }
        })

        viewModel.userMoneyData.observe(this, Observer {
            stopRefresh()
            Log.d("xiecheng", "携程请求的数据userMoneyData-----------------------${it.balance}")
            viewModel.fundItem.mUserMoney = it
            viewModel.bottomData.add(viewModel.fundItem)
            viewModel.refreshBottomData()
            setOrderAndServiceItem(it)
        })

    }

    private fun setOrderAndServiceItem(it: UserMoneyModel) {
        viewModel.orderItem.mUserMoney = it
        viewModel.bottomData.add(viewModel.orderItem)
        viewModel.bottomData.add(viewModel.serviceItem)
        viewModel.refreshBottomData()
    }

    private fun isShowApplyShopManagerNotifyWindow(data: UserAsKeeperInfo) {
//        if (data != null && viewModel.mMineBottomAdapter != null) {
        // 升级通知和卓越店主
//            if (data.showNotifyDialog()) {
//                val window: UserUpdateSuccessPopWindow = UserUpdateSuccessPopWindow.getInstance(mContext)
//                window.showPopupWindow()
//            }

//            if (data.isGold() && data.is_gold_notify() == 0) {
//                val window: BrilliantApplySuccessPopWindow =
//                    BrilliantApplySuccessPopWindow.getInstance(mContext)
//                window.showPopupWindow()
//            }
//        }


        // 降级通知
//        if (data != null && adapter != null) {
//            if (!data.isGold() && data.is_gold_down_notify() === 0) {
//                val window: BrilliantDownVipPopWindow =
//                    BrilliantDownVipPopWindow.getSingle(mContext)
//                window.showPopupWindow()
//            }
//            if (!data.isGold() && !data.isShopKeeper() && data.is_down_notify() === 0) {
//                val window: VipDownToNormalPopWindow = VipDownToNormalPopWindow.getSingle(mContext)
//                window.showPopupWindow()
//            }
//        }


    }

    @SuppressLint("SetTextI18n")
    private fun setUserInfo(userInfo: UserInfoModel) {
        Glide.with(activity!!)
            .load(userInfo.memberProfile.avatar)
            .apply(ImageOptionUtils.getCircleAvatarOptions())
            .transform(
                GlideCircleWithBorder(
                    activity,
                    2,
                    Color.parseColor("#62EAE5")
                )
            ).into(ivAvatar)


        tvNickName.text = userInfo.member.username
        if (userInfo.member.type > 2) {
            tvNumber.text = "名义店主:${userInfo.member.id}"
        } else {
            tvNumber.text = "ID:${userInfo.member.id}"
        }


        if (userInfo.member.level == CommonOb.MYCB_LEVEL.gold_vip) {
            tv_renew_fee.visibility = View.VISIBLE
            tv_renew_fee.text = "绩效评定"
            // 名字和id要重新布局
            val params = layoutLevel2.layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = R.id.ivAvatar
            params.topMargin = AppDensityUtils.dip2px(activity, 5f)
            layoutLevel2.layoutParams = params
            val params2 = layoutCode.layoutParams as ConstraintLayout.LayoutParams
            params2.topToBottom = R.id.layoutLevel2
            params2.topMargin = AppDensityUtils.dip2px(activity, 5f)
            layoutCode.layoutParams = params2

        } else {
            tv_renew_fee.visibility = View.INVISIBLE
            iv_user_questions.visibility = View.INVISIBLE
            // 名字和id要重新布局
            val params = layoutLevel2.layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = R.id.ivAvatar
            params.topMargin = 45
            layoutLevel2.layoutParams = params
            val params2 = layoutCode.layoutParams as ConstraintLayout.LayoutParams
            params2.topToBottom = R.id.layoutLevel2
            params2.topMargin = AppDensityUtils.dip2px(activity, 5f)
            layoutCode.layoutParams = params2
        }


        if (userInfo.member.level > CommonOb.MYCB_LEVEL.svip) {
            viewModel.bottomData.add(viewModel.shopItem)
        } else {
            viewModel.bottomData.remove(viewModel.shopItem)
        }
        viewModel.refreshBottomData()




//        viewModel.launchString(
//            requestApi = viewModel.mMineRepo.mService.getString(HttpConstant.MEMBER_MEMBER_INFO, WeakHashMap()
//            ),
//            successResult = {
//                Log.d("xiecheng","携程请求的数据successResult-----------------------${it}")
////                val msg = JSON.parseObject(it).getJSONObject("data").getString("message")
//
//
//            },
//            errorResult = {
//                Log.d("xiecheng","携程请求的数据errorResult-------------------- ${it.code} ${it.errMsg} ==")
//                ToastUtils.showShort(""+it.code + it.errMsg)
//            },
//            completeResult = {
//                Log.d("xiecheng","携程请求的数据completeResult----------------------")
//
//            })

    }


    private fun initRecycleView() {
        recycle_mine.layoutManager =
            RecycleViewLayoutManagerUtils.getLinearLayoutMangerVertical(activity!!)
        viewModel.mMineBottomAdapter = MineBottomAdapter(arrayListOf())
        recycle_mine.adapter = viewModel.mMineBottomAdapter

    }

    override fun onRefresh() {
        viewModel.clearBottomData()
        viewModel.requestUserInfo()
        viewModel.requestUserApplyShopManager()
        viewModel.requestUserInfoMoney()
    }

    private fun stopRefresh(){
        if (swipe_refresh != null && swipe_refresh.isRefreshing){
            swipe_refresh.isRefreshing = false
        }
    }
}