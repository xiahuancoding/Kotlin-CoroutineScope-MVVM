package com.example.reviewmycp.utlis;


import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

/** 2020-5-25
 *  itfreshman
 *  RecycelView管理器提供者
 */
public class RecycleViewLayoutManagerUtils {


    /**
     * 获得垂直布局的适配器
     * @param context
     * @return
     */
    public static LinearLayoutManager getLinearLayoutMangerVertical(Context context){
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        return layoutManager;
    }


    /**
     * 获得水平布局的适配器
     * @param context
     * @return
     */
    public static LinearLayoutManager getLinearLayoutMangerHorizontal(Context context){
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        return layoutManager;
    }

}
