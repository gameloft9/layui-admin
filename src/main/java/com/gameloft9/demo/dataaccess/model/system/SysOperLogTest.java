package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 应用日志Model
 * */
@Data
public class SysOperLogTest implements Serializable{
    private static final long serialVersionUID = 1L;
    /**id*/
    private String id;
    /**用户Id*/
    private String userId;
    /**登录名*/
    private String loginName;
    /**ip地址*/
    private String ipAddr;
    /**操作类型*/
    private String operType;
    /**操作日期*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**描述*/
    private String memo;

}