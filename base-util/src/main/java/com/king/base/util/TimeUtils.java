/*
 Copyright © 2015, 2016 Jenly Yu <a href="mailto:jenly1314@gmail.com">Jenly</a>

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 */
package com.king.base.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class TimeUtils {

    private static final String TAG = "TimeUtils";

    /** 时间格式：yyyyMMddHHmmss */
    public static final String FORMAT_Y_TO_S = "yyyyMMddHHmmss";

    /** 时间格式：yyyy-MM-dd HH:mm:ss */
    public static final String FORMAT_Y_TO_S_EN = "yyyy-MM-dd HH:mm:ss";

    /** 时间格式：yyyy-MM-dd HH:mm */
    public static final String FORMAT_Y_TO_M_EN = "yyyy-MM-dd HH:mm";

    /** 时间格式：yyyy-MM-dd */
    public static final String FORMAT_Y_TO_D = "yyyy-MM-dd";

    /** 时间格式：yyyy年MM月dd日 HH时mm分ss秒 */
    public static final String FORMAT_Y_TO_S_CN = "yyyy年MM月dd日 HH时mm分ss秒";

    /** 时间格式：yyyy年MM月dd HH时mm分 */
    public static final String FORMAT_Y_TO_M_CN = "yyyy年MM月dd HH时mm分";

    /** 时间格式：yyyy年MM月dd日 */
    public static final String FORMAT_Y_TO_D_CN = "yyyy年MM月dd日";

    /** 时间格式：yyyyMMdd */
    public static final String FORMAT_YMD = "yyyyMMdd";
    /** 时间格式：HHmmss */
    public static final String FORMAT_HMS = "HHmmss";
    /** 时间格式：HH:mm */
    public static final String FORMAT_H_TO_MIN = "HH:mm";
    /** 时间格式：yyyy/MM/dd/ HH:mm:ss */
    public static String FORMAT_Y_M_D = "yyyy/MM/dd/ HH:mm:ss";
    /** 时间格式：HH:mm:ss */
    public static String FORMAT_H_M_S = "HH:mm:ss";

    //------------------------------------
    /**
     * 异常时间
     */
    private static final int EXCEPTION_TIME = -1;

    /**
     * 一分钟的毫秒值
     */
    public static final long ONE_MINUTE = 60 * 1000;

    /**
     * 一小时的毫秒值
     */
    public static final long ONE_HOUR = 60 * ONE_MINUTE;

    /**
     * 一天的毫秒值
     */
    public static final long ONE_DAY = 24 * ONE_HOUR;

    /**
     * 一月的毫秒值
     */
    public static final long ONE_MONTH = 30 * ONE_DAY;

    /**
     * 一年的毫秒值
     */
    public static final long ONE_YEAR = 12 * ONE_MONTH;

    private TimeUtils(){
        throw new AssertionError();
    }

    /**
     * 格式化日期
     * @param time 要格式的时间
     * @param fromFormat 时间当前格式
     * @param toFormat 时间格式化后的格式
     * @return
     */
    public static String formatDate(String time, String fromFormat, String toFormat) {
        if (TextUtils.isEmpty(time)) {
            return null;
        } else if (TextUtils.isEmpty(fromFormat)
                || TextUtils.isEmpty(toFormat)) {
            return time;
        }

        String dateTime = time;


        String dataStr = null;
        try {
            SimpleDateFormat oldFormat = new SimpleDateFormat(fromFormat);
            SimpleDateFormat newFormat = new SimpleDateFormat(toFormat);
            dataStr = newFormat.format(oldFormat.parse(dateTime));
        } catch (ParseException e) {
            LogUtils.w( "date format failed: time=" + time + ", fromFormat="
                    + fromFormat + ", toFormat=" + toFormat, e);
            return time;
        }

        return dataStr;
    }

    /**
     * 格式化日期
     * @param time 时间
     * @param toFormat 格式
     * @return
     */
    public static String formatDate(String time, String toFormat) {
        if (time == null || "".equals(time.trim()))
            return "";

        String fromFormat = null;
        if (time.length() == 6) {
            fromFormat = "HHmmss";
        } else if (time.length() == 8) {
            fromFormat = "yyyyMMdd";
        } else if (time.length() == 14) {
            fromFormat = "yyyyMMddHHmmss";
        } else {
            return time;
        }

        try {
            SimpleDateFormat oldFormat = new SimpleDateFormat(fromFormat);
            SimpleDateFormat newFormat = new SimpleDateFormat(toFormat);
            return newFormat.format(oldFormat.parse(time));
        } catch (ParseException e) {
            LogUtils.w("date format failed: time=" + time + ", fromFormat="
                    + fromFormat + ", toFormat=" + toFormat, e);
            return time;
        }
    }

    /**
     * 格式化日期
     * @param time 时间毫秒
     * @param toFormat 格式
     * @return
     */
    public static String formatDate(long time, String toFormat){
        try {
            Date date = new Date(time);
            SimpleDateFormat newFormat = new SimpleDateFormat(toFormat);
            return newFormat.format(date);
        }catch (Exception e){

        }
        return "";
    }

    /**
     * 格式化当前日期
     * @param toFormat 格式
     * @return
     */
    public static String getCurrentDate(String toFormat) {
        try{
            SimpleDateFormat format = new SimpleDateFormat(toFormat);
            return format.format(new Date());
        }catch (Exception e){
            LogUtils.w(e);
        }
        return "";
    }

    /**
     * 格式化日期
     * @param date
     * @param toFormat
     * @return
     */
    public static String formatDate(Date date, String toFormat) {
        try{
            SimpleDateFormat format = new SimpleDateFormat(toFormat);
            return format.format(date);
        }catch (Exception e){
            LogUtils.w(e);
        }
        return "";
    }

    /**
     * 解析时间为{@link Date}
     * @param time
     * @return
     */
    public static Date parse(String time) {
        if (time == null || "".equals(time.trim()))
            return null;

        String fromFormat = null;
        if (time.length() == 6) {
            fromFormat = "HHmmss";
        } else if (time.length() == 8) {
            fromFormat = "yyyyMMdd";
        } else if (time.length() == 14) {
            fromFormat = "yyyyMMddHHmmss";
        } else {
            return null;
        }

        try {
            SimpleDateFormat oldFormat = new SimpleDateFormat(fromFormat);
            return oldFormat.parse(time);
        } catch (ParseException e) {
            LogUtils.e("date format failed: time=" + time + ", fromFormat=" + fromFormat);
            return null;
        }
    }


}
