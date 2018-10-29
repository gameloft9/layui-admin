package com.gameloft9.demo.mgrframework.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gameloft9 on 2017/12/5.
 */
public class IPUtil {
    /**
     * 获取请求ip地址
     * @param request
     * @return String 返回类型
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
