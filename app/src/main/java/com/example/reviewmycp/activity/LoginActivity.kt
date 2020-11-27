package com.example.reviewmycp.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.alibaba.fastjson.JSON
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.reviewmycp.R
import com.example.reviewmycp.net.HttpConstant
import com.example.reviewmycp.utlis.LattePreference
import com.example.reviewmycp.utlis.SmsType
import com.example.reviewmycp.utlis.isChinaPhone
import com.example.reviewmycp.viewmodel.LoginVM
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity<LoginVM>(){


    private var countdownTime: io.reactivex.Observer<Long>? = null


    override fun layoutId(): Int = R.layout.activity_login


    override fun initView(savedInstanceState: Bundle?) {
        login_sure_lly.isEnabled = false

    }

    override fun initData() {
        viewModel.loginData.observe(this, Observer {
            LogUtils.d("itfreshman v1/member/login-old = $it")
            // 保存登录token到本地
            LogUtils.d("login_token 保存 = ${it.member.accessToken}")
            LattePreference.addCustomAppProfile("login_token", it.member.accessToken)
            MainActivity.jumpActivity(this)
//            PersonInfoActivity.jumpActivity(this)


        })

        viewModel.loginSendCodeData.observe(this, Observer {
            LogUtils.d("itfreshman v1/send//code = $it")

        })
    }

    override fun registerListener() {
        super.registerListener()
        login_sure_lly?.setOnClickListener {
            if (viewModel.loginType == LoginVM.LOGIN_PWD){
                loginLocalPwd()
            }else{
                loginLocalCode()
            }
        }

        iv_close_login?.setOnClickListener {
            closeActivity()
        }

        tvChangeSign?.setOnClickListener {
            switchLoginType()
        }

        login_ed_send?.addTextChangedListener(phoneTextWatch)
        iv_account_input_close?.setOnClickListener {
            login_ed_send?.setText("")
        }
        login_ed_pwd?.addTextChangedListener(pwdTextWatch)
        iv_pwd_input_close?.setOnClickListener {
            login_ed_pwd?.setText("")
        }

        tvSend?.setOnClickListener {
            sendCode()
        }
    }

    private fun sendCode() {
        viewModel.phone = login_ed_send.text.toString()
        if (viewModel.phone?.isEmpty()!!){
            showMessage("请输入手机号")
            return
        }

        if (!isChinaPhone(viewModel.phone!!)){
            showMessage("手机号码格式不正确")
            return
        }

        // 示例：get请求，返回一个完整的json串
        val params = WeakHashMap<String,Any>()
        params["phone"] = viewModel.phone
        params["type"] = SmsType.CODE_SIGN_IN
        viewModel.launchString(
            requestApi = viewModel.loginRepo.mService.getString(HttpConstant.SEND_CODE,params),
            successResult = {
                Log.d("xiecheng","携程请求的数据successResult-----------------------${it}")
                val msg = JSON.parseObject(it).getJSONObject("data").getString("message")
                showMessage(msg)
                skillTime(60)


            },
            errorResult = {
                Log.d("xiecheng","携程请求的数据errorResult-------------------- ${it.code} ${it.errMsg} ==")
                ToastUtils.showShort(""+it.code + it.errMsg)
            },
            completeResult = {
                Log.d("xiecheng","携程请求的数据completeResult----------------------")

            })

    }

    private fun skillTime(mTime:Int) {
        //延迟一秒计时
        countdownTime = object : io.reactivex.Observer<Long>{
            override fun onComplete() {
                //倒计时结束所需要做的操作
                if (tvSend != null) {
                    tvSend.setTextColor(Color.parseColor("#63DBD7"))
                    tvSend.text = "获取验证码"
                }
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: Long) {
                if (tvSend != null) {
                    tvSend.setTextColor(Color.parseColor("#B8B8B8"))
                    tvSend.text = StringBuilder(16).append("重发(").append(t)
                        .append("s)")
                }
            }

            override fun onError(e: Throwable) {

            }

        }

        Observable.interval(1, TimeUnit.SECONDS)
            .take(mTime.toLong())
            .map<Long> { aLong ->
                //强制格式化一次剩余时间（延时 mTime 秒 最后倒计时结束显示 0 则需要多 减去 1）
                mTime - 1 - aLong
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(countdownTime as io.reactivex.Observer<Long>)


    }

    private fun switchLoginType() {
        if (viewModel.loginType == LoginVM.LOGIN_PWD){
            viewModel.loginType = LoginVM.LOGIN_CODE
            login_ed_pwd?.hint = "请输入验证码"
            tvChangeSign?.text = "密码登录"
            tvSend?.visibility = View.VISIBLE
            login_ed_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()

        }else{
            viewModel.loginType = LoginVM.LOGIN_PWD
            login_ed_pwd?.hint = "请输入密码"
            tvChangeSign?.text = "验证码登录"
            tvSend?.visibility = View.GONE
            login_ed_pwd.transformationMethod = PasswordTransformationMethod.getInstance()

        }

    }

    private fun closeActivity() {
        finish()
    }

    private fun loginLocalCode() {
        viewModel.phone = login_ed_send?.text.toString()
        viewModel.code = login_ed_pwd?.text.toString()

        if (checkInputInfo()) return
        val params = WeakHashMap<String,Any>()
        params["account"] =  viewModel.phone
        params["code"] =  viewModel.code
        viewModel.launchString(
            requestApi = viewModel.loginRepo.mService.postString(HttpConstant.USER_LOGIN_CODE,
                viewModel.loginRepo.params2Body(params)),
            successResult = {
                Log.d("xiecheng","携程请求的数据successResult-----------------------${it}")


            },
            errorResult = {
                Log.d("xiecheng","携程请求的数据errorResult-------------------- ${it.code} ${it.errMsg} ==")
                ToastUtils.showShort(""+it.code + it.errMsg)
            },
            completeResult = {
                Log.d("xiecheng","携程请求的数据completeResult----------------------")

            })
//        viewModel.loginByVerifyCode()
    }

    private fun loginLocalPwd() {
        viewModel.phone = login_ed_send?.text.toString()
        viewModel.pwd = login_ed_pwd?.text.toString()

        if (checkInputInfo()) return
        viewModel.loginByPwd()

    }

    private fun checkInputInfo(): Boolean {
        if (viewModel.phone?.isEmpty()!!) {
            showMessage("手机号码不能为空")
            return true
        }

        if (viewModel.loginType == LoginVM.LOGIN_PWD){
            if (viewModel.pwd?.isEmpty()!!) {
                showMessage("密码不能为空")
                return true
            }
        }
        if (viewModel.loginType == LoginVM.LOGIN_CODE){
            if (viewModel.code?.isEmpty()!!) {
                showMessage("验证码不能为空")
                return true
            }
        }

        if (!isChinaPhone(viewModel.phone!!)){
            showMessage("手机号码格式不正确")
            return true
        }


        return false
    }

    private val phoneTextWatch = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val temp = s.toString()
            if (!TextUtils.isEmpty(temp)) {
                tvSend.isClickable = true
                login_sure_lly.background = ContextCompat.getDrawable(
                    this@LoginActivity,
                    R.drawable.ec_shape_sign_in_sure_blue
                )
                login_sure_lly.isEnabled = true
                iv_account_input_close.visibility = View.VISIBLE
            } else {
                tvSend.isClickable = false
                iv_account_input_close.visibility = View.GONE
                login_sure_lly.background = ContextCompat.getDrawable(
                    this@LoginActivity,
                    R.drawable.ec_shape_sign_in_sure_gray
                )
                login_sure_lly.isEnabled = false
            }

        }
    }

    private val pwdTextWatch = object :TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val temp = s.toString()
            if (!TextUtils.isEmpty(temp)) {
                iv_pwd_input_close.visibility = View.VISIBLE
            } else {
                iv_pwd_input_close.visibility = View.GONE
            }
        }
    }


    companion object {
        fun jumpActivity(context: Context){
            context.startActivity(Intent(context,LoginActivity::class.java))
        }

    }


}