package com.example.reviewmycp.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.aleyn.mvvm.event.Message
import com.alibaba.fastjson.JSON
import com.blankj.utilcode.util.ToastUtils
import com.example.reviewmycp.R
import com.example.reviewmycp.net.ResponseThrowable
import com.example.reviewmycp.net.retrofit
import com.example.reviewmycp.utlis.ViewModelFactory
import com.example.reviewmycp.viewmodel.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import qiu.niorgai.StatusBarCompat
import retrofit2.Call
import java.lang.reflect.ParameterizedType
import kotlin.coroutines.CoroutineContext


/**
 * activity基类
 *
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(), CoroutineScope {


    protected lateinit var viewModel: VM
    private var dialog: MaterialDialog? = null
    private lateinit var job: Job
    var compositeDisposable = CompositeDisposable() // Rxjava方法的注销管理，防止内存泄漏


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        initBeforeSetContentView()
        setContentView(layoutId())
        createViewModel()
        lifecycle.addObserver(viewModel)
        registerDefUIChange()
        initView(savedInstanceState)
        initData()
        registerListener()
        if (isNeedStatusBar()){
            StatusBarCompat.translucentStatusBar(this, true)
            StatusBarCompat.changeToLightStatusBar(this)
        }
    }

    abstract fun layoutId():Int
    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initData()

    // 如果某些Activity需要再setContentView之前初始化一些东西，比如全屏，去掉标题栏，重写此方法
    open fun initBeforeSetContentView(){

    }
    // 如果需要写控件的监听，集中在这里写
    open fun registerListener(){

    }

    // 是否需要沉浸式状态栏,默认需要，不要重写这个方法传false
    open fun isNeedStatusBar():Boolean = true

    fun showMessage(msg:String){
        ToastUtils.showShort(msg)
    }


    /**
     * 注册 UI 事件
     */
    private fun registerDefUIChange() {
        viewModel.defUI.showDialog.observe(this, Observer {
            showLoading()
        })
        viewModel.defUI.dismissDialog.observe(this, Observer {
            dismissLoading()
        })
        viewModel.defUI.toastEvent.observe(this, Observer {
            ToastUtils.showShort(it)
        })
        viewModel.defUI.msgEvent.observe(this, Observer {
            handleEvent(it)
        })
    }

    open fun handleEvent(msg: Message) {}


    /**
     * 打开等待框
     */
    private fun showLoading() {
        if (dialog == null) {
            dialog = MaterialDialog(this)
                .cancelable(false)
                .cornerRadius(8f)
                .customView(R.layout.custom_progress_dialog_view, noVerticalPadding = true)
                .lifecycleOwner(this)
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



    /**
     * 创建 ViewModel
     */
    @Suppress("UNCHECKED_CAST")
    private fun createViewModel() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val tp = type.actualTypeArguments[0]
            val tClass = tp as? Class<VM> ?: BaseViewModel::class.java
//            viewModel = ViewModelProvider(this, ViewModelFactory()).get(tClass) as VM
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(tClass) as VM
        }
    }


    /**
     * 请求网络数据，返回json原始数据
     */
    fun launchString(
        isShowDialog: Boolean = true,
        requestApi :(Call<ResponseBody>),
        successResult: (String) -> Unit = {},
        errorResult: (ResponseThrowable) -> Unit = {
            viewModel.defUI.toastEvent.postValue("${it.code}:${it.errMsg}")
        },
        completeResult: () -> Unit = {}
    ){

        if (isShowDialog) viewModel.defUI.showDialog.call()
        viewModel.launchUI {
            retrofit<ResponseBody> {
                runBlocking {
                    api = requestApi
                }

                onSuccess {
                    Log.d("xiecheng","携程请求的数据onSuccess-------")
                    viewModel.defUI.dismissDialog.call()
                    val json = it.string()
                    if(handleSuccessCode(json)){
                        successResult(json)
                    }else{
                        val code = JSON.parseObject(json).getIntValue("code")
                        val msg = JSON.parseObject(json).getString("message")
                        ToastUtils.showShort("$code:$msg")
                    }

                }

                onFail { msg, code ->
                    Log.d("xiecheng","携程请求的数据fail-----------------------${msg} == $code")
                    viewModel.defUI.dismissDialog.call()
                    errorResult(ResponseThrowable(code,msg))
                }

                onComplete {
                    Log.d("xiecheng","携程请求的数据compelete---------------")
                    viewModel.defUI.dismissDialog.call()
                    completeResult()
                }

            }
        }
    }

    /**
     * 处理接口请求后的业务code
     */
    private fun handleSuccessCode(json:String):Boolean {
        val code = JSON.parseObject(json).getIntValue("code")
        return code == 0

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }

}