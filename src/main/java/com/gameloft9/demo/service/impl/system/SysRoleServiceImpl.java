package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.SysUserRoleTestMapper;
import com.gameloft9.demo.mgrframework.utils.UserInfoUtil;
import com.gameloft9.demo.service.api.system.SysRoleService;
import com.gameloft9.demo.dataaccess.dao.system.SysRoleTestMapper;
import com.gameloft9.demo.dataaccess.model.system.SysRoleTest;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.UUIDUtil;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by gameloft9 on 2017/12/4.
 */
@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService{

    @Autowired
    SysRoleTestMapper sysRoleTestDao;
    @Autowired
    SysUserRoleTestMapper sysUserRoleTestMapper;

    /**
     * 获取所有角色
     * */
    public List<SysRoleTest> getAll(String page, String limit, String roleName, String isSuper){
        PageRange pageRange = new PageRange(page,limit);
        return sysRoleTestDao.selectAll(pageRange.getStart(),pageRange.getEnd(),roleName,isSuper);
    }

    /**
     * 获取所有角色个数
     * */
    public int countGetAll(String roleName,String isSuper){
        return sysRoleTestDao.countGetAll(roleName,isSuper);
    }

    /**
     * 删除角色
     * @param roleId  角色id
     * */
    public Boolean deleteRoleById(String roleId){
        //判断该角色下面是否还有用户,有用户则不能删除该角色
        int userCount = sysUserRoleTestMapper.countByRoleId(roleId);
        CheckUtil.check(userCount==0,"该角色下有用户，无法删除");

        sysRoleTestDao.deleteByPrimaryKey(roleId);
        return true;
    }

    /**
     * 添加角色
     * @param roleName  角色名称
     * @param isSuper   是否超级管理员
     * */
    public String addRole(String roleName,String isSuper){
        CheckUtil.notBlank(roleName,"角色名称为空");
        CheckUtil.notBlank(isSuper,"是否超级管理员不能为空");

        //角色名称不能重复
        SysRoleTest roleTest = sysRoleTestDao.getByRoleName(roleName);
        CheckUtil.check(roleTest == null,"该角色名称已经存在");

        SysRoleTest role = new SysRoleTest();
        role.setId(UUIDUtil.getUUID());
        role.setRoleName(roleName);
        role.setIsSuper(isSuper);
        role.setCreateUser(UserInfoUtil.getUser().toString());
        sysRoleTestDao.insertSelective(role);

        return role.getId();
    }


    /**
     * 获取角色
     * @param roleId 角色id
     */
    public SysRoleTest getRole(String roleId){
        CheckUtil.notBlank(roleId,"角色id为空");
        return sysRoleTestDao.selectByPrimaryKey(roleId);
    }

    /**
     * 更新角色
     * @param roleId 角色id
     * @param roleName 角色名称;
     * @param isSuper 是否超级管理员 1-是，0-否
     * */
    public Boolean updateRole(String roleId,String roleName,String isSuper){
        CheckUtil.notBlank(roleId,"角色id为空");

        //角色名称不能重复
        SysRoleTest roleTest = sysRoleTestDao.getByRoleName(roleName);
        CheckUtil.check(roleTest == null || roleTest.getRoleName().equals(roleName),"该角色名称已经存在");

        SysRoleTest role = new SysRoleTest();
        role.setId(roleId);
        role.setRoleName(roleName);
        role.setIsSuper(isSuper);
        role.setUpdateUser(UserInfoUtil.getUser().toString());
        role.setUpdateTime(new Date());
        sysRoleTestDao.updateByPrimaryKeySelective(role);

        return true;
    }
}
