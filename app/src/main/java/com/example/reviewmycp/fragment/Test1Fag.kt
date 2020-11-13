package com.example.reviewmycp.fragment

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.example.reviewmycp.R
import com.example.reviewmycp.utlis.TextViewLinesUtils
import com.example.reviewmycp.viewmodel.TestVM
import kotlinx.android.synthetic.main.test1.*

class Test1Fag : BaseFragment<TestVM>() {


    var isExpand = false


    override fun layoutId(): Int = R.layout.test1


    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.d("testfag:$viewModel")
        btn_set_name.setOnClickListener {
            viewModel.name = "哈哈哈"
            LogUtils.d("testfag:${viewModel.name}")

        }


        tv_desc.setOnClickListener {
            if (isExpand){
                isExpand = false
                tv_desc.maxLines = 2
            }else{
                isExpand = true
                tv_desc.maxLines = Int.MAX_VALUE
            }

            TextViewLinesUtils.toggleEllipsize(
                activity, tv_desc, 2, "香港在全球金融中心指数上一直名列为全球第三大金融中心香港在全球金融中心指数上一直名列为全球第三大金融中心，连续第20年获得全球最自由经济体系评级，经济自由度指数排名第一。",
                "..展开全部",
                R.color.ec_text_333333, isExpand
            )
        }


        TextViewLinesUtils.toggleEllipsize(
            activity, tv_desc, 2, "香港在全球金融中心指数上一直名列为全球第三大金融中心香港在全球金融中心指数上一直名列为全球第三大金融中心，连续第20年获得全球最自由经济体系评级，经济自由度指数排名第一。",
            "..展开全部",
            R.color.ec_text_333333, isExpand
        )
    }

}