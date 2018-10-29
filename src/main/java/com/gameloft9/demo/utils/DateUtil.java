package com.gameloft9.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * Created by gameloft9 on 2017/12/15.
 */
public class DateUtil {

    /**默认格式字符串*/
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期转字符串
     *
     * @param data   日期
     * @param format 格式
     * @return String 日期字符串
     */
    public static String date2Str(Date data, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(data);
    }

    /**
     * 日期转字符串
     *
     * @param data 日期
     * @return String 日期字符串
     */
    public static String date2Str(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        return sdf.format(data);
    }

    /**
     * 字符串转日期
     * @param str 日期字符串
     * @return Date 日期对象
     */
    public static Date str2Date(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        Date res;
        try {
            res = sdf.parse(str);
            return res;
        } catch (Exception e) {
            throw new RuntimeException("日期解析异常");
        }
    }

    /**
     * 测试
     * */
    public static void main(String[] args) {
        String str = "2017-10-11 12:12:12";
        Date date = new Date();

        System.out.println(str2Date(str));
        System.out.println(date2Str(date));
        System.out.println(date2Str(date, "yyyy-MM-dd"));

    }

}
