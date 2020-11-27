package com.example.reviewmycp.view.popwindow;


import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.reviewmycp.R;
import com.example.reviewmycp.viewmodel.NoViewModel;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;
import retrofit2.Call;

/** 2020-04-11
 *  itfreashman
 *  用户等级升级成功查询弹窗
 *  只弹一次
 */
public class UserUpdateSuccessPopWindow extends BasePopupWindow {

    private int id;
    public List<Call> mCalls = new ArrayList<>();
    private NoViewModel viewModel;

    private static UserUpdateSuccessPopWindow instance;



    public UserUpdateSuccessPopWindow(Context context) {
        super(context);
        // 不能区域以外点击取消
        setAllowDismissWhenTouchOutside(false);
        init();
    }

    private void init() {
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this, new ViewModelProvider.NewInstanceFactory()).get(NoViewModel.class);

        AppCompatTextView btnSure = findViewById(R.id.btn_user_update_success);
        btnSure.setOnClickListener(view->{
            // 请求已读
            requsetUserApplyRead();
        });
    }

    private void requsetUserApplyRead() {
//        Call call = RestClient.builder()
//                .url(ApiMethod.HAD_NOTIFY_USER_BE_MANAGER)
//                .success(response -> {
////                    JsonEle data = JSON.parseObject(response).getBoolean("data");
////                    LogUtils.d("itfreashman data = "+data);
//                    dismiss();
//
//                })
//                .error(new GlobleError())
//                .build()
//                .post();
//
//        mCalls.add(call);
    }


    /**
     * 单例模式
     */
    public static UserUpdateSuccessPopWindow getInstance(Context context){
        if (instance == null){
            instance = new UserUpdateSuccessPopWindow(context);
        }

        return instance;
    }


    public void setId(int id){
        this.id = id;
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popwindow_user_update_success);
    }


    @Override
    public void onDismiss() {
        super.onDismiss();
        for (Call call : mCalls) {
            if (!call.isCanceled()) {
                call.cancel();
            }
        }

        if (instance != null){
            instance = null;
        }

    }
}
