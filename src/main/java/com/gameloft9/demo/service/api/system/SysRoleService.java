package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysRoleTest;

import java.util.List;

/**
 * Created by gameloft9 on 2017/12/4.
 */
public interface SysRoleService {

    /**
     * 获取所有角色
     * @param page 页序
     * @param limit 分页大小
     * */
    List<SysRoleTest> getAll(String page, String limit, String roleName, String isSuper);

    /**
     * 获取所有角色个数
     * */
    int countGetAll(String roleName,String isSuper);

    /**
     * 删除角色
     * @param roleId  角色id
     * */
    Boolean deleteRoleById(String roleId);


    /**
     * 添加角色
     * @param roleName  角色名称
     * @param isSuper   是否超级管理员
     * */
    String addRole(String roleName,String isSuper);


    /**
     * 获取角色
     * @param roleId 角色id
     */
    SysRoleTest getRole(String roleId);

   /**
    * 更新角色
    * @param roleId 角色id
    * @param roleName 角色名称;
    * @param isSuper 是否超级管理员 1-是，0-否
    * */
    Boolean updateRole(String roleId,String roleName,String isSuper);
}
