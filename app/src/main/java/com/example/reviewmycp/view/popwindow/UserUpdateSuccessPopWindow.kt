package com.example.reviewmycp.view.popwindow

import android.content.Context
import android.util.Log
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.alibaba.fastjson.JSON
import com.blankj.utilcode.util.ToastUtils
import com.example.reviewmycp.R
import com.example.reviewmycp.net.HttpConstant
import com.example.reviewmycp.net.retrofit
import com.example.reviewmycp.utlis.RepositoryUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import razerdp.basepopup.BasePopupWindow
import retrofit2.Call
import java.util.*


class UserUpdateSuccessPopWindow(context: Context) : BasePopupWindow(context) {

    private var dialog: MaterialDialog? = null
    private val baseRepo by lazy { RepositoryUtils.getBaseRepository() }


    init {

        // 不能区域以外点击取消
        isAllowDismissWhenTouchOutside = false
        val btn = findViewById<View>(R.id.btn_user_update_success)
        btn.setOnClickListener {
            // 请求已读
            requestUserApplyRead(baseRepo.mService.postString(HttpConstant.HAD_NOTIFY_USER_BE_MANAGER,
                baseRepo.params2Body(WeakHashMap())),isShowDialog = false)
        }
    }


    companion object {

        @Volatile
        private var INSTANCE: UserUpdateSuccessPopWindow? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserUpdateSuccessPopWindow(context).also { INSTANCE = it }
            }
    }


    override fun onCreateContentView(): View {
        return createPopupById(R.layout.popwindow_user_update_success)
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
                    Log.d("xiecheng", "携程请求的数据UserUpdateSuccessPopWindow onSuccess  -------${json}")
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