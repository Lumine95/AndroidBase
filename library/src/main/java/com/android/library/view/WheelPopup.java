package com.android.library.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.library.R;
import com.android.library.view.wheelView.WheelView;
import com.android.library.view.wheelView.adapter.NumericWheelAdapter;


/**
 * Created by Administrator on 2016/1/22.
 * 时间选择器
 */
public class WheelPopup extends PopupWindow {


    private final WheelView m_wvSelect;
    public TextView m_tvCancelBtn, m_tvOkBtn;
    private View mMenuView;
    private Activity activity;
    private IOnSelectLister mSelectListener;
    private String [] m_strValues;

    public void setSelectListener(IOnSelectLister mSelectListener) {
        this.mSelectListener = mSelectListener;
    }

    public WheelPopup(final Context context, final String[] strValues) {
        super(context);

        if (context instanceof Activity) {
            activity=(Activity) context;
        }
        this.m_strValues = strValues;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popup_wheel_select, null);
        m_tvCancelBtn = (TextView) mMenuView.findViewById(R.id.tv_cancel_btn);
        m_tvOkBtn = (TextView) mMenuView.findViewById(R.id.tv_ok_btn);
        m_wvSelect = (WheelView) mMenuView.findViewById(R.id.wv_select);

        m_wvSelect.getCurrentItem();

        //取消按钮
        m_tvCancelBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                //设置透明度，改变popurwindow上边视图
                setParams(1f);
                dismiss();
            }
        });

        //取消按钮
        m_tvOkBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                //设置透明度，改变popurwindow上边视图
              setParams(1f);
                dismiss();

               String value = strValues[m_wvSelect.getCurrentItem()];

                mSelectListener.getSelect(value, m_wvSelect.getCurrentItem());
            }
        });



        //设置按钮监听

        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
       // this.setAnimationStyle(R.style.popup_delete);
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        //设置透明度，改变popurwindow上边视图
           setParams(0.5f);

        //  监听popupWindow消失
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                dismiss();
                setParams(1f);
            }
        });

        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        //设置透明度，改变popurwindow上边视图
                      setParams(1f);
                        dismiss();

                    }
                }
                return true;
            }
        });

        m_wvSelect.setViewAdapter(new StringArrayAdapter(activity, strValues));

    }


    public void setParams(float v) {
        //设置透明度，改变popurwindow上边视图
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = v;
        activity.getWindow().setAttributes(params);
    }

    private class StringArrayAdapter extends NumericWheelAdapter {

        // Index of current item
        int currentItem;

        public StringArrayAdapter(Context context, String[] pramers) {
            super(context, pramers);
        }

        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            if (currentItem == getCurrentItem()) {
                view.setTextColor(0xFF222222);
                //view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            } else {
                view.setTextColor(0xFF999999);
            }
          //  view.setTypeface(Typeface.DEFAULT);
            view.setTextSize(15);
            view.setPadding(0, 15, 0, 15);

        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            currentItem = index;
            return super.getItem(index, cachedView, parent);
        }
    }

    public interface IOnSelectLister {
        String getSelect(String argValue, int position);
    }
}
