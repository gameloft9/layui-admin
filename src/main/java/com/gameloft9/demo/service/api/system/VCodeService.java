package com.gameloft9.demo.service.api.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码服务
 * Created by gameloft9 on 2017/12/6.
 */
public interface VCodeService {

    /**
     * 输出验证码到客户端
     * */
    void outPutVCode(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
