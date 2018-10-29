package com.gameloft9.demo.utils;

/**
 * 密码工具类
 * Created by gameloft9 on 2017/12/21.
 */
public class PasswordUtil {

    // 定义验证码的字符表
    private static String chars = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    private static int LENGTH = 6;//密码长度

    /**
     *获取随机密码
     * */
    public static String getRandomPwd(){
        char[] rands = new char[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            int rand = (int) (Math.random() * chars.length());
            rands[i] = chars.charAt(rand);
        }

        return new String(rands);
    }

}
