package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.SysUserRoleTestMapper;
import com.gameloft9.demo.dataaccess.dao.system.UserMapper;
import com.gameloft9.demo.dataaccess.model.system.SysRoleTest;
import com.gameloft9.demo.dataaccess.model.system.SysUserRoleTest;
import com.gameloft9.demo.dataaccess.model.system.UserTest;
import com.gameloft9.demo.service.api.system.SysUserService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.service.beans.system.SysUserResponse;
import com.gameloft9.demo.service.beans.system.UserAddRequest;
import com.gameloft9.demo.service.beans.system.UserUpdateRequest;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.PasswordUtil;
import com.gameloft9.demo.utils.SHA1;
import com.gameloft9.demo.utils.UUIDUtil;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by gameloft9 on 2017/11/28.
 */
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    UserMapper dao;
    @Autowired
    SysUserRoleTestMapper sysUserRoleTestMapper;

    /**
     * 验证用户
     * @param loginName 登录名
     * @param password 密码（为了不增加复杂度，这里不进行加密，使用明文）
     * */
    public Boolean validateUser(String loginName,String password){
        int n = dao.countUserByNameAndPwd(loginName,password);
        return n>0?true:false;
    }

    /**
     * 根据登录名获取用户
     * @param loginName 登录名
     * */
    public UserTest getByLoginName(String loginName){
        return dao.getByLoginName(loginName);
    }

    /**
     * 获取用户角色列表
     * @param userId 用户id
     * */
    public List<String> getRoleNames(String userId){
        return sysUserRoleTestMapper.getRoleNamesByUserId(userId);
    }

    /**
     * 分页获取用户列表
     *
     * @param page         页序
     * @param limit        分页大小
     * @param loginName     登录名
     * @param realName     姓名
     * @param status       状态 0-禁用 1-启用
     */
    public List<UserTest> getAll(String page, String limit, String loginName, String realName, String status) {
        PageRange pageRange = new PageRange(page, limit);
        return dao.getAll(pageRange.getStart(), pageRange.getEnd(), loginName, realName, status);
    }

    /**
     * 获取用户列表大小
     *
     * @param loginName     登录名
     * @param realName     姓名
     * @param status       状态 0-禁用 1-启用
     */
    public int countGetAll(String loginName, String realName, String status) {
        return dao.countGetAll(loginName, realName, status);
    }

    /**
     * 根据id删除用户
     * @param id 用户id
     * */
    public Boolean deleteById(String id){
        CheckUtil.notBlank(id,"用户id为空");

        dao.deleteByPrimaryKey(id);
        //删除用户角色关系
        sysUserRoleTestMapper.deleteByUserId(id);

        return true;
    }

    /**
     * 根据id获取记录
     * @param id 主键
     * */
    public SysUserResponse getById(String id){
        CheckUtil.notBlank(id,"用户id为空");

        SysUserResponse userResponse = new SysUserResponse();
        UserTest user = dao.selectByPrimaryKey(id);
        List<SysRoleTest> sysRoleTestList = sysUserRoleTestMapper.getRolesByUserId(id);

        //获取角色id列表
        if(sysRoleTestList != null&&!sysRoleTestList.isEmpty()){
            List<String> roleIdList = new ArrayList<String>();
            for(SysRoleTest role:sysRoleTestList){
                roleIdList.add(role.getId());
            }
            userResponse.setRoleIdList(roleIdList);
        }

        userResponse.setId(user.getId());
        userResponse.setLoginName(user.getLoginName());
        userResponse.setMobile(user.getMobile());
        userResponse.setOrgId(user.getOrgId());
        userResponse.setOrgName(user.getOrgName());
        userResponse.setRealName(user.getRealName());

        return userResponse;
    }

    /**
     * 全量更新
     * @param userRequest 记录
     * */
    public Boolean updateUser(UserUpdateRequest userRequest){
        CheckUtil.notNull(userRequest,"更新用户为空");

        //登录名不能重复
        UserTest old = dao.getByLoginName(userRequest.getLoginName());
        CheckUtil.check(old == null||old.getId().equals(userRequest.getId()),"用户名已存在");

        //更新用户
        UserTest user = dao.selectByPrimaryKey(userRequest.getId());
        user.setOrgName(userRequest.getOrgName());
        user.setOrgId(userRequest.getOrgId());
        user.setLoginName(userRequest.getLoginName());
        user.setRealName(userRequest.getRealName());
        user.setMobile(userRequest.getMobile());
        dao.updateByPrimaryKey(user);

        //更新用户角色
        addUserRole(user.getId(),userRequest.getRoleIdList());

        return true;
    }

    /**
     * 添加用户
     * @param user 记录
     * @return String 主键id
     * */
    @Transactional(rollbackForClassName = "Exception")
    public String addUser(UserAddRequest user){
        CheckUtil.notNull(user,"添加用户为空");
        CheckUtil.notBlank(user.getLoginName(),"登录名为空");
        CheckUtil.notBlank(user.getOrgId(),"组织机构为空");
        CheckUtil.notBlank(user.getOrgName(),"组织机构为空");

        //登录名不能重复
        UserTest old = dao.getByLoginName(user.getLoginName());
        CheckUtil.check(old == null,"该用户名已经存在");

        UserTest newUser = new UserTest();
        newUser.setId(UUIDUtil.getUUID());
        newUser.setLoginName(user.getLoginName());
        newUser.setIsForbidden("0");
        newUser.setPassword(Constants.INIT_PWD);
        newUser.setMobile(user.getMobile());
        newUser.setOrgId(user.getOrgId());
        newUser.setOrgName(user.getOrgName());
        newUser.setRealName(user.getRealName());
        dao.insertSelective(newUser);

        log.info("用户新增成功，开始关联用户角色");
        List<String> roleIdList = user.getRoleIdList();
        addUserRole(newUser.getId(),roleIdList);

        return newUser.getId();
    }

    /**
     * 初始化密码
     * @param userId 用户id
     * */
    public String initPwd(String userId){
        String pwd = PasswordUtil.getRandomPwd();
        String sha1Pwd = (new SHA1()).getDigestOfString(pwd);//SHA1加密

        UserTest user = new UserTest();
        user.setId(userId);
        user.setPassword(sha1Pwd);
        dao.updateByPrimaryKeySelective(user);

        return pwd;
    }

    /**
     * 更换密码
     * @param  loginName 登录名
     * @param  newPwd 新密码
     * */
    public Boolean changePwd(String loginName,String oldPwd,String newPwd){
        CheckUtil.notBlank(loginName,"登录名为空");
        CheckUtil.notBlank(oldPwd,"旧密码为空");
        CheckUtil.notBlank(newPwd,"新密码为空");

        UserTest user = dao.getByLoginName(loginName);
        CheckUtil.notNull(user,"该用户不存在");

        int n = dao.countUserByNameAndPwd(loginName,oldPwd);
        CheckUtil.check(n>0,"密码错误");

        user.setPassword(newPwd);
        dao.updateByPrimaryKeySelective(user);

        return true;
    }


    /*************************私有方法区***********************************/
    /**
     * 关联用户角色
     * @param id 用户id
     * @param roleIdList 角色id列表
     * */
    private Boolean addUserRole(String id,List<String> roleIdList){
        CheckUtil.notBlank(id,"用户id为空");

        //先批量删除再添加
        sysUserRoleTestMapper.deleteByUserId(id);

        if(roleIdList == null||roleIdList.isEmpty()){
            return true;
        }

        List<SysUserRoleTest> userRoleTestList = new Stack<SysUserRoleTest>();
        for(String roleId:roleIdList){
            SysUserRoleTest userRoleTest = new SysUserRoleTest();
            userRoleTest.setId(UUIDUtil.getUUID());
            userRoleTest.setUserId(id);
            userRoleTest.setRoleId(roleId);
            userRoleTestList.add(userRoleTest);
        }

        //批量插入
        sysUserRoleTestMapper.batchInsert(userRoleTestList);
        return true;
    }
}
