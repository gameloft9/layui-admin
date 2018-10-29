package com.gameloft9.demo.service.beans.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录成功返回bean
 * Created by gameloft9 on 2017/12/11.
 */
@Data
public class LoginResponse implements Serializable{

    /**
     * 用户id
     * */
    private String userId;
    /**
     * 登录名
     * */
    private String loginName;

    /**
     * web上下文
     * */
    private String webContext;
}
