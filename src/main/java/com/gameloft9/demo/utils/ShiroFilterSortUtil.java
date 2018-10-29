package com.gameloft9.demo.utils;

import java.util.Random;

/**
 * shiro filter chain 定义顺序工具
 * Created by gameloft9 on 2017/12/14.
 */
public class ShiroFilterSortUtil {

    /**
     * 获取[100,999]之间的随机数字,用于生成访问authc访问的filter顺序号
     * */
    public static int getRandomAuthorSort(){
        int result;
        do{
            result = (int)(Math.random()*1000);
        }while (result < 100);
        return result;
    }

    /**
     * 获取100以内的随机数字，用于生成anon访问的filter顺序号
     * */
    public static int getRandomAnonSort(){
       return (int)(Math.random()*100);
    }
}
