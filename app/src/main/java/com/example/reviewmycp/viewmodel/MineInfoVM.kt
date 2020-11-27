package com.example.reviewmycp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.reviewmycp.adapter.MineBottomAdapter
import com.example.reviewmycp.model.MineBottomModel
import com.example.reviewmycp.model.UserAsKeeperInfo
import com.example.reviewmycp.model.UserInfoModel
import com.example.reviewmycp.model.UserMoneyModel
import com.example.reviewmycp.net.HttpConstant
import com.example.reviewmycp.utlis.RepositoryUtils
import java.util.*

class MineInfoVM : BaseViewModel(){


    val mMineRepo by lazy { RepositoryUtils.getBaseRepository() }

    val applyItem = MineBottomModel(itemType = MineBottomModel.MINE_ITEM_APPLY)
    val fundItem = MineBottomModel(itemType = MineBottomModel.MINE_ITEM_FUND)
    val shopItem = MineBottomModel(itemType = MineBottomModel.MINE_ITEM_SHOP)
    val orderItem = MineBottomModel(itemType = MineBottomModel.MINE_ITEM_ORDER)
    val serviceItem = MineBottomModel(itemType = MineBottomModel.MINE_ITEM_SERVICE)


    var mMineBottomAdapter : MineBottomAdapter? = null
    val bottomData = mutableListOf<MineBottomModel>()
    var userInfoData = MutableLiveData<UserInfoModel>()
    var userMoneyData = MutableLiveData<UserMoneyModel>()
    var userApplyShopData = MutableLiveData<UserAsKeeperInfo>()
    var userApplyShopSwitchData = MutableLiveData<Boolean>()


    fun requestUserInfo(){
        launchOnlyResult({mMineRepo.mService.requestUserInfo(HttpConstant.MEMBER_MEMBER_INFO, WeakHashMap())},{
            userInfoData.postValue(it)
        })
    }

    fun requestUserInfoMoney(){
        launchOnlyResult({mMineRepo.mService.requestUserMoney(HttpConstant.MEMBER_MEMBER_STAT, WeakHashMap())},{
            userMoneyData.postValue(it)
        },isShowDialog = false)
    }



    fun requestUserApplyShopManager(){
        launchOnlyResult({mMineRepo.mService.requestApplyShopManager(HttpConstant.APPLY_SHOP_MANAGER,
            mMineRepo.params2Body(WeakHashMap()))},{
            userApplyShopData.postValue(it)
        })
    }

    fun requestUserApplyShopManagerSwitch(){
        launchOnlyResult({mMineRepo.mService.requestApplyShopManagerSwitch(HttpConstant.IS_SHOW_USER_APPLY,
            mMineRepo.params2Body(WeakHashMap()))},{
            userApplyShopSwitchData.postValue(it)
        },isShowDialog = false)
    }


    fun refreshBottomData(){
         bottomData.sortBy { it.itemType }
         mMineBottomAdapter?.setNewData(bottomData)
    }

}