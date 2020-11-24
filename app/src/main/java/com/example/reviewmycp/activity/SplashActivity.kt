package com.example.reviewmycp.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Window
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.LogUtils
import com.example.reviewmycp.R
import com.example.reviewmycp.net.RetrofitCoroutineDSL
import com.example.reviewmycp.utlis.ConfigKeys
import com.example.reviewmycp.utlis.Latte
import com.example.reviewmycp.utlis.LattePreference
import com.example.reviewmycp.utlis.rxbus.RxBus
import com.example.reviewmycp.utlis.rxbus.RxBusAction
import com.example.reviewmycp.view.TipPopMainPopWindow
import com.example.reviewmycp.viewmodel.SplashVM


class SplashActivity : BaseActivity<SplashVM>(){



    override fun layoutId(): Int = R.layout.activity_splash

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.isShowData.observe(this,Observer{
            LogUtils.d("itfreashman isShowData = $it")
        })


    }


    @SuppressLint("CheckResult")
    override fun initData() {
        checkAllowProtocol()
        viewModel.disposable = RxBus.getDefault().toObservable(RxBusAction.SPLASH_CLICK_WINDOW, String::class.java)
            .subscribe {response->
                if (response == "1") {
                    jumpToMain()
                } else { // 仍然不同意
                    finish()
                }
            }

    }

    private fun checkAllowProtocol() {
        val isAllowProtocol: Boolean = LattePreference.getAppFlag("tip_agree")
        if (isAllowProtocol){
            jumpToMain()
        }else{
            showProtocol()
        }

    }

    private fun showProtocol() {
        val content =
            "<p style=\"text-align: center;\"><span style=\"font-size: 18pt;\"><strong>注册协议及隐私条例</strong></span><u></u></p>\n" +
                    "<p><span style=\"font-size: 8pt;\">【特别提示】请仔细阅读《名义初品用户注册协议》和《名义初品隐私条例》（尤其是加粗划线的内容）并确定了解我们对您个人信息的处理规则。阅读过程中，如您有任何疑问，可联系我们的客服咨询，如您不同意协议中的任何条款，您应立即停止访问名义初品。 阅读协议的过程中，如果您不同意相关协议或其中任何条款约定，您应立即停止使用名义初品。</span><u></u></p>\n" +
                    "<span style=\"color: #63DBD7;\"><strong><a style=\"color: #63DBD7;\" href=\"" +
                    Latte.getConfiguration(ConfigKeys.API_HOST) + "h5/index.html#/rule1" +
                    "\" target=\"_blank\" rel=\"noopener\">《名义初品用户注册协议》</a></strong></span>\n" +
                    "<br><span style=\"color: #63DBD7;\"><strong><a style=\"color: #63DBD7;\" href=\"" +
                    Latte.getConfiguration(ConfigKeys.API_HOST) +
                    "h5/index.html#/rule2" +
                    "\" target=\"_blank\" rel=\"noopener\">《名义初品隐私条例》</a></strong></span></br>\n"

        val popwindow = TipPopMainPopWindow(this,content)
        popwindow.showPopupWindow()
    }

    private fun jumpToMain() {
//        LoginActivity.jumpActivity(this)
//        TestActivity.jumpActivity(this)
//        PersonInfoActivity.jumpActivity(this)
        MainActivity.jumpActivity(this)
        finish()
    }


    override fun initBeforeSetContentView() {
        super.initBeforeSetContentView()
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }


    override fun onResume() {
        super.onResume()
        viewModel.isShowOrDismissRequest()

    }


    override fun onDestroy() {
        super.onDestroy()
        if (viewModel.disposable != null){
            viewModel.disposable?.dispose()
            viewModel.disposable = null
        }

    }


}