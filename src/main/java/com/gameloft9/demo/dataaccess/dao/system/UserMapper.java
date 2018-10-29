package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.UserTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gameloft9 on 2017/11/28.
 */
public interface UserMapper {

    int deleteByPrimaryKey(String id);

    int insert(UserTest record);

    int insertSelective(UserTest record);

    UserTest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserTest record);

    int updateByPrimaryKey(UserTest record);

    /**
     * 查找用户
     *
     * @param loginName 登录名
     * @param password  密码（为了不增加复杂度，这里不进行加密，使用明文）
     */
    Integer countUserByNameAndPwd(@Param("loginName") String loginName,
                                  @Param("password") String password);

    /**
     * 根据loginName获取用户
     *
     * @param loginName 登录名
     */
    UserTest getByLoginName(@Param("loginName") String loginName);

    /**
     * 分页获取用户列表
     */
    List<UserTest> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("loginName") String loginName,
            @Param("realName") String realName,
            @Param("isForbidden") String isForbidden);

    /**
     * 获取用户列表大小
     */
    int countGetAll(@Param("loginName") String loginName,
                    @Param("realName") String realName,
                    @Param("isForbidden") String isForbidden);
}
