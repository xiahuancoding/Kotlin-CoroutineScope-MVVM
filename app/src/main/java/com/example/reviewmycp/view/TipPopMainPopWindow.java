package com.example.reviewmycp.view;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.reviewmycp.R;
import com.example.reviewmycp.utlis.LattePreference;
import com.example.reviewmycp.utlis.rxbus.RxBus;
import com.example.reviewmycp.utlis.rxbus.RxBusAction;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Android Studio.
 * User: LeeJiTing
 * Date: 2020/1/3
 * Time: 9:52
 */
public class TipPopMainPopWindow extends BasePopupWindow {
    private Context mContext;
    private TipPopSurePopWindow surePopWindow;
    private boolean isAgreeTip = true;
    private String content;

    public TipPopMainPopWindow(Context context, String content) {
        super(context);
        this.mContext = context;
        this.content = content;
        setAllowDismissWhenTouchOutside(false);
        initView();
    }

    private void initView() {
        //同意
        AppCompatTextView tipContent = findViewById(R.id.tip_content);
        if (!TextUtils.isEmpty(content)) {
            tipContent.setText(Html.fromHtml(content));
//            tipContent.setMovementMethod(WebLinkMethod.getInstance(mContext));
        }
        findViewById(R.id.tip_agree).setOnClickListener(v -> {
            if (isShowing()) {
                dismiss();
            }
            LattePreference.setAppFlag("tip_agree", isAgreeTip);
            RxBus.getDefault().send(RxBusAction.SPLASH_CLICK_WINDOW, "1");

        });
        //不同意
        findViewById(R.id.tip_disable).setOnClickListener(v -> {
            surePopWindow = new TipPopSurePopWindow(mContext);
            surePopWindow.setOnShowTopInterface(() -> {
                showPopupWindow();
            });
            surePopWindow.showPopupWindow();
            if (isShowing()) {
                dismiss();
            }
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.tip_double_dialog_layout);
    }


}
