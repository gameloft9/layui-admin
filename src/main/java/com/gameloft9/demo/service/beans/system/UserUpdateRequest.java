package com.gameloft9.demo.service.beans.system;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * 用户更新request bean
 * Created by gameloft9 on 2017/12/21.
 */
@Data
public class UserUpdateRequest implements Serializable {
    @NotBlank
    private String id;
    /**登录名*/
    @NotBlank
    private String loginName;
    /**真实姓名*/
    private String realName;
    /**所属机构id*/
    @NotBlank
    private String orgId;
    /**所属机构名称*/
    @NotBlank
    private String orgName;
    /**手机号码*/
    private String mobile;
    /**所属角色列表*/
    private List<String> roleIdList;
}
