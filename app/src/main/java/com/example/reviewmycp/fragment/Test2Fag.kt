package com.example.reviewmycp.fragment

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.example.reviewmycp.R
import com.example.reviewmycp.viewmodel.TestVM
import kotlinx.android.synthetic.main.test2.*

class Test2Fag:BaseFragment<TestVM>(){

    override fun layoutId(): Int = R.layout.test2


    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.d("testfag:$viewModel")
        btn_get_name.setOnClickListener {
            LogUtils.d("testfag:${viewModel.name}")
            tv_show.text = viewModel.name
        }

    }

}