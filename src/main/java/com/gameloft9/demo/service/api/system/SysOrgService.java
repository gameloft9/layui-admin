package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysOrganizeTest;
import com.gameloft9.demo.service.beans.system.OrgNodeResponse;

import java.util.List;

/**
 * 组织机构服务
 * Created by gameloft9 on 2017/12/19.
 */
public interface SysOrgService {

    /**
     * 获取所有组织机构
     * */
    List<OrgNodeResponse> getAll();

    /**
     * 根据id获取组织机构
     * @param id 主键
     * */
    SysOrganizeTest getById(String id);

    /**
     * 更新组织机构
     * @param org 组织机构信息
     * */
    Boolean update(SysOrganizeTest org);

    /**
     * 删除组织机构
     * @param id 组织机构id
     * */
    Boolean deleteById(String id);

    /**
     * 添加组织机构
     * @param org 组织机构信息
     * */
    Boolean add(SysOrganizeTest org);

}
