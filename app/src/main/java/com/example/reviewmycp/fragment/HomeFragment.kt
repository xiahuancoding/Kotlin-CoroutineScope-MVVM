package com.example.reviewmycp.fragment

import android.os.Bundle
import com.example.reviewmycp.R
import com.example.reviewmycp.viewmodel.NoViewModel

class HomeFragment : BaseFragment<NoViewModel>() {

    override fun layoutId(): Int = R.layout.fragment_home
    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initData() {
    }

}