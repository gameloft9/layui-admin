package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gameloft9 on 2017/11/28.
 */
@Data
public class UserTest implements Serializable{
    private static final long serialVersionUID = 1L;
    /**id*/
    private String id;
    /**登录名*/
    private String loginName;
    /**密码*/
    private String password;
    /**真实姓名*/
    private String realName;
    /**是否禁用*/
    private String isForbidden;
    /**创建时间*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**更新时间*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    /**所属机构id*/
    private String orgId;
    /**所属机构名称*/
    private String orgName;

    private String mobile;

    private String icon;
}
