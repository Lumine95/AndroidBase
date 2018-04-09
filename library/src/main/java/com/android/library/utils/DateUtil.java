package com.android.library.utils;

import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 1bu2bu on 2015/8/19.
 * <p/>
 * 日期转换工具类
 */
public class DateUtil {
    private static Thread timeThread;
    private static String str;

    /**
     * 把时间戳转为时间
     *
     * @param d
     * @param type
     * @return
     */
    public static String getDate(Long d, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        String date = sdf.format(new Date(d * 1000));
        return date + "";
    }

    /**
     * 转化时间输入时间与当前时间的间隔
     *
     * @param timestamp
     * @return
     */
    public static String convertTime(long timestamp) {
        long currentSeconds = System.currentTimeMillis() / 1000;
        long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数
        String timeStr = null;
        if (timeGap > 24 * 60 * 60) {// 1天以上
            timeStr = timeGap / (24 * 60 * 60) + "天前";
        } else if (timeGap > 60 * 60) {// 1小时-24小时
            timeStr = timeGap / (60 * 60) + "小时前";
        } else if (timeGap > 60) {// 1分钟-59分钟
            timeStr = timeGap / 60 + "分钟前";
        } else {// 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 获取当前系统年月
     *
     * @return
     */
    public static String getCurrentYearMonth() {
        String str = getSysTimeType("yyyy-MM");
        return str;
    }

    /**
     * 获取当前时间（自定义时间格式）
     *
     * @param type
     * @return
     */
    public static String getSysTimeType(String type) {
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }


    /**
     * 获取当前系统时间（自定义时间格式）
     *
     * @param type
     * @return
     */
    public static String getCurrentStrDate(String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        String date = sdf.format(new Date());
        return date + "";
    }


    /**
     * 将字符串转为时间戳
     *
     * @param time    2016-1-25
     * @param argType
     * @return
     */
    public static long getStringToDate(String time, String argType) {
        String strType = argType;
        if (argType == null) {
            strType = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(strType);
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }

    /**
     * 获取今天开始的 0点 0 分
     *
     * @return
     */
    public static long getToday() {
        String strToday = DateUtil.getSysTimeType("yyyy-MM-dd日");
        long today = DateUtil.getStringToDate(strToday + " 00:00:00", null);
        return today;
    }


    /**
     * 获取今天 结束的 0点 0 分
     *
     * @return
     */
    public static long getTomorrow() {
        long today = getToday();
        return today + 24 * 60 * 60;
    }

    /**
     * 获取后天开始的 0点 0 分
     *
     * @return
     */
    public static long getAfterTomorrow() {
        long today = getTomorrow();
        return today + 24 * 60 * 60;
    }

    /**
     * 获取网络时间
     *
     * @return
     */
    public static String getNetTime(final String type) {
        timeThread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        URL url = null;// 取得资源对象
                        long date = 0;
                        try {
                            url = new URL("http://www.baidu.com");
                            URLConnection uc = url.openConnection();// 生成连接对象
                            uc.connect(); //发出连接
                            date = uc.getDate();// 取得网站日期时间
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        SimpleDateFormat formatter = new SimpleDateFormat(type);
                        str = formatter.format(date);
                    }
                }
        );
        timeThread.start();
        try {
            timeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return str;
    }
}
