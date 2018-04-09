package com.android.library.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.library.R;


/********************************************************************
 * 转动圆形对话框
 *******************************************************************/

public class CustomProgressDialog {
    Context context;
    Dialog dialog;

    View contentView;
    // @ViewInject(R.id.tv_msg)
    TextView tvMsg;
    // @ViewInject(R.id.iv_loading)
    ImageView ivLoading;

    String titleText, messageText;
    String okText, cancelText;

    public CustomProgressDialog(Context context) {
        this.context = context;
    }

    void init() {
        contentView = View
                .inflate(context, R.layout.custom_progress_dialog, null);
        tvMsg = (TextView) contentView.findViewById(R.id.tv_msg);
        ivLoading = (ImageView) contentView.findViewById(R.id.iv_loading);
        // InjectHelper.init(this, contentView);
//		Glide.with(context).load(R.drawable.xinchengshi_loding).centerCrop()
//        /*
//         * 缺省的占位图片，一般可以设置成一个加载中的进度GIF图
//         */
//        .placeholder(null).crossFade().into(ivLoading);
    }

    public Dialog create() {
        init();

        dialog = new Dialog(context, R.style.AppTheme_Dialog_NoTitleBar);
        dialog.setContentView(contentView);

        setMessage(null);

        return dialog;
    }

    public void show() {
        if (dialog == null) {
            return;
        }
         dialog.show();
        contentView.post(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable animationDrawable = (AnimationDrawable) ivLoading
                        .getBackground();
                animationDrawable.start();
            }
        });
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void setMessage(int msgId) {
        setMessage(contentView.getResources().getString(msgId));
    }

    public void setMessage(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            tvMsg.setText(msg);
            tvMsg.setVisibility(View.VISIBLE);
        } else {
            tvMsg.setVisibility(View.GONE);
        }
    }

    public Dialog getDialog() {
        return dialog;
    }
}