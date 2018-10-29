package com.gameloft9.demo.service.beans.system;

import com.gameloft9.demo.dataaccess.model.system.SysMenuTest;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 返回给客户端的菜单信息
 * Created by gameloft9 on 2017/12/12.
 */
@Data
public class SysMenuTestExtend extends SysMenuTest implements Serializable{

    /**
     * 父菜单编码
     * */
    private String parentMenuCode;

    /**
     * 父菜单名称
     * */
    private String parentMenuName;

    /**
     * 菜单所属角色id列表
     * */
    private List<String> roleIdList;

}
