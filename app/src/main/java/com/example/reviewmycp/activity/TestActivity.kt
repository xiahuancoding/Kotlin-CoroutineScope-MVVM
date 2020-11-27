package com.example.reviewmycp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import com.example.reviewmycp.R
import com.example.reviewmycp.fragment.Test1Fag
import com.example.reviewmycp.fragment.Test2Fag
import com.example.reviewmycp.viewmodel.TestVM

class TestActivity:BaseActivity<TestVM>(){


    override fun layoutId(): Int = R.layout.activity_test

    override fun initView(savedInstanceState: Bundle?) {

        supportFragmentManager.beginTransaction().add(R.id.fl_1,Test1Fag()).commit()
        supportFragmentManager.beginTransaction().add(R.id.fl_2, Test2Fag()).commit()

    }

    override fun initData() {
    }

    override fun isNeedStatusBar(): Boolean = false

    companion object {
        fun jumpActivity(context: Context){
            context.startActivity(Intent(context,TestActivity::class.java))
        }
    }

}