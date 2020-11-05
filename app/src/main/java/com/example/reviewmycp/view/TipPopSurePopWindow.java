package com.example.reviewmycp.view;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.AppUtils;
import com.example.reviewmycp.R;
import com.example.reviewmycp.utlis.rxbus.RxBus;
import com.example.reviewmycp.utlis.rxbus.RxBusAction;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Android Studio.
 * User: LeeJiTing
 * Date: 2020/1/3
 * Time: 10:20
 */
public class TipPopSurePopWindow extends BasePopupWindow {
    private Context mContext;
    private onShowTopInterface onShowTopInterface;

    public TipPopSurePopWindow(Context context) {
        super(context);
        this.mContext = context;
        setAllowDismissWhenTouchOutside(false);
        initView();
    }

    private void initView() {

        String string = mContext.getResources().getString(R.string.example_string_tip_sure_title);
        String format = String.format(string, AppUtils.getAppName());
        AppCompatTextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(format);
        findViewById(R.id.tip_disable).setOnClickListener(v -> {
            if (isShowing()) {
                dismiss();
            }
            RxBus.getDefault().send(RxBusAction.SPLASH_CLICK_WINDOW, "2");

        });
        findViewById(R.id.tip_agree).setOnClickListener(v -> {
            if (isShowing()) {
                dismiss();
                if (onShowTopInterface != null) {
                    onShowTopInterface.showTop();
                }
            }
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.tip_double_sure_dialog_layout);
    }

    public interface onShowTopInterface {
        void showTop();
    }

    public void setOnShowTopInterface(TipPopSurePopWindow.onShowTopInterface onShowTopInterface) {
        this.onShowTopInterface = onShowTopInterface;
    }
}
