package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysAccessPermissionTest;

import java.util.List;

/**
 * 访问权限服务
 * Created by gameloft9 on 2017/12/4.
 */
public interface SysAccessPermissionService {

    /**
     * 获取所有访问权限配置
     * */
    List<SysAccessPermissionTest> getAll();
}
