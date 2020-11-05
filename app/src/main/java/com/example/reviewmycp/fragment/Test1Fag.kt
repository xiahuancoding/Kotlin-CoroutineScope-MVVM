package com.example.reviewmycp.fragment

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.example.reviewmycp.R
import com.example.reviewmycp.viewmodel.TestVM
import kotlinx.android.synthetic.main.test1.*

class Test1Fag : BaseFragment<TestVM>(){


    override fun layoutId(): Int = R.layout.test1


    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.d("testfag:$viewModel")
        btn_set_name.setOnClickListener {
            viewModel.name = "哈哈哈"
            LogUtils.d("testfag:${viewModel.name}")

        }


    }

}