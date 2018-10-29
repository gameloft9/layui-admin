package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysOrganizeTest;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.SysOrgService;
import com.gameloft9.demo.service.beans.system.OrgNodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * 组织机构
 * Created by gameloft9 on 2017/12/19.
 */
@Controller
@Slf4j
@RequestMapping("/org")
public class SysOrgController {

    @Autowired
    SysOrgService sysOrgServiceImpl;

    /**
     * 获取所有组织机构
     * */
    @RequestMapping(value = "/getAll.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getAll(){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Collection<OrgNodeResponse>>(sysOrgServiceImpl.getAll());
    }

    /**
     * 获取组织机构
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult get(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<SysOrganizeTest>(sysOrgServiceImpl.getById(id));
    }

    /**
     * 更新组织机构
     * @param org 组织机构信息
     * */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult update(SysOrganizeTest org){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysOrgServiceImpl.update(org));
    }

    /**
     * 删除组织机构
     * @param id 组织机构id
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult delete(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysOrgServiceImpl.deleteById(id));
    }

    /**
     * 添加组织机构
     * @param org 组织机构信息
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult add(SysOrganizeTest org){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysOrgServiceImpl.add(org));
    }

}
