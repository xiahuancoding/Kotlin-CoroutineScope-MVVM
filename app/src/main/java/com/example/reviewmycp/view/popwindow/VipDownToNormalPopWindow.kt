package com.example.reviewmycp.view.popwindow

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.alibaba.fastjson.JSON
import com.blankj.utilcode.util.ToastUtils
import com.example.reviewmycp.R
import com.example.reviewmycp.net.HttpConstant
import com.example.reviewmycp.net.retrofit
import com.example.reviewmycp.utlis.RepositoryUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import razerdp.basepopup.BasePopupWindow
import retrofit2.Call
import java.util.*

class VipDownToNormalPopWindow(context: Context) : BasePopupWindow(context){

    private var dialog: MaterialDialog? = null
    private val baseRepo by lazy { RepositoryUtils.getBaseRepository() }

    init {

        // 不能区域以外点击取消
        isAllowDismissWhenTouchOutside = false
        popupGravity = Gravity.CENTER
        val btn = findViewById<View>(R.id.tv_know)
        btn.setOnClickListener {
            // 请求已读
            requestUserApplyRead(baseRepo.mService.postString(HttpConstant.LEVEL_DOWN_NOTIFY,
                baseRepo.params2Body(WeakHashMap())),isShowDialog = false)
        }
    }



    override fun onCreateContentView(): View {
        return createPopupById(R.layout.popwindow_vip_down_to_normal)
    }


    companion object {

        @Volatile
        private var INSTANCE: VipDownToNormalPopWindow? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: VipDownToNormalPopWindow(context).also { INSTANCE = it }
            }
    }



    private fun requestUserApplyRead(
        requestApi: (Call<ResponseBody>),
        isShowDialog: Boolean = true) {

        if (isShowDialog) showLoading()
        GlobalScope.launch {

            retrofit<ResponseBody> {
                runBlocking {
                    api = requestApi
                }

                onSuccess {
                    dismissLoading()
                    val json = it.string()
                    Log.d("xiecheng", "携程请求的数据VipDownToNormalPopWindow onSuccess  -------${json}")
                    if (handleSuccessCode(json)) {
                        dismiss()
                    } else {
                        val code = JSON.parseObject(json).getIntValue("code")
                        val msg = JSON.parseObject(json).getString("message")
                        ToastUtils.showShort("$code:$msg")
                    }

                }

                onFail { msg, code ->
                    Log.d("xiecheng", "携程请求的数据fail-----------------------${code} == $msg")
                    dismissLoading()
                }

                onComplete {
                    Log.d("xiecheng", "携程请求的数据compelete---------------")
                    dismissLoading()
                }

            }
        }


    }

    /**
     * 处理接口请求后的业务code
     */
    private fun handleSuccessCode(json: String): Boolean {
        val code = JSON.parseObject(json).getIntValue("code")
        return code == 0

    }

    /**
     * 打开等待框
     */
    private fun showLoading() {
        if (dialog == null) {
            dialog = MaterialDialog(context)
                .cancelable(false)
                .cornerRadius(8f)
                .customView(R.layout.custom_progress_dialog_view, noVerticalPadding = true)
                .maxWidth(R.dimen.dialog_width)
        }
        dialog?.show()

    }

    /**
     * 关闭等待框
     */
    private fun dismissLoading() {
        dialog?.run { if (isShowing) dismiss() }
    }

}