package com.example.reviewmycp.fragment

import android.os.Bundle
import com.example.reviewmycp.R
import com.example.reviewmycp.viewmodel.NoViewModel

class MessageFragment : BaseFragment<NoViewModel>(){

    override fun layoutId(): Int = R.layout.fragment_message
    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initData() {
    }


}