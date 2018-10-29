package com.gameloft9.demo.service.beans.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 新增用户请求bean
 * Created by gameloft9 on 2017/12/21.
 */
@Data
public class UserAddRequest implements Serializable{

    /**登录名*/
    private String loginName;
    /**真实姓名*/
    private String realName;
    /**所属机构id*/
    private String orgId;
    /**所属机构名称*/
    private String orgName;
    /**手机号码*/
    private String mobile;
    /**所属角色列表*/
    private List<String> roleIdList;
}
