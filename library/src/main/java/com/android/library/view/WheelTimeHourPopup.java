package com.android.library.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
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

import java.util.Calendar;


/**
 * Created by Administrator on 2016/1/22.
 */
public class WheelTimeHourPopup extends PopupWindow {

    public TextView m_tvCancelBtn, m_tvOkBtn;
    private View mMenuView;
    private Activity activity;
    private IOnSelectLister mSelectListener;
    private String m_strDate;
    private WheelView hour;

    private int curItemHour = 11;


    public void setSelectListener(IOnSelectLister mSelectListener) {
        this.mSelectListener = mSelectListener;
    }

    public WheelTimeHourPopup(final Context context, String argDate) {
        super(context);

        if (context instanceof Activity) {
            activity = (Activity) context;
        }
        this.m_strDate = argDate;


        initView();

        setListener();
    }

    private void initView() {


        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popup_wheel_time_hour_select, null);
        m_tvCancelBtn = (TextView) mMenuView.findViewById(R.id.tv_cancel_btn);
        m_tvOkBtn = (TextView) mMenuView.findViewById(R.id.tv_ok_btn);

        hour = (WheelView) mMenuView.findViewById(R.id.time);

        hour.setViewAdapter(new DateNumericAdapter(activity, 1, 48));
        hour.setCurrentItem(curItemHour);


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

    }

    private void setListener() {
        //取消按钮
        m_tvCancelBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                //设置透明度，改变popurwindow上边视图
                setParams(1f);
                dismiss();
            }
        });

        //确定按钮
        m_tvOkBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                //设置透明度，改变popurwindow上边视图

                int h = hour.getCurrentItem() + 1;

                setParams(1f);
                dismiss();

                mSelectListener.getSelect(h + "");
            }
        });

        //设置按钮监听

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

    }

    public void setParams(float v) {
        //设置透明度，改变popurwindow上边视图
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = v;
        activity.getWindow().setAttributes(params);
    }

    /**
     * Updates day wheel. Sets max days according to selected month and year
     */
    void updateDays(WheelView year, WheelView month, WheelView day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,
                calendar.get(Calendar.YEAR) + year.getCurrentItem());
        calendar.set(Calendar.MONTH, month.getCurrentItem());

        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        day.setViewAdapter(new DateNumericAdapter(activity, 1, maxDays));
        if (day.getCurrentItem() > maxDays - 1) {
            day.setCurrentItem(maxDays - 1, true);
        } else {
            day.setCurrentItem(day.getCurrentItem());
        }

    }

    /**
     * Adapter for numeric wheels. Highlights the current value.
     */
    private class DateNumericAdapter extends NumericWheelAdapter {

        // Index of current item
        int currentItem;

        /**
         * Constructor
         */
        public DateNumericAdapter(Context context, int minValue, int maxValue) {
            super(context, minValue, maxValue);
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
            view.setTypeface(Typeface.DEFAULT);
            view.setTextSize(22);

        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            currentItem = index;
            return super.getItem(index, cachedView, parent);
        }
    }


    public interface IOnSelectLister {
        public String getSelect(String argValue);
    }

}
