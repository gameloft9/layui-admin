package com.gameloft9.demo.security;

import com.gameloft9.demo.service.api.system.SysAccessPermissionService;
import com.gameloft9.demo.dataaccess.model.system.SysAccessPermissionTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * shiro filter放在Java代码里配置，方便从数据库动态加载配置
 * Created by gameloft9 on 2017/12/4.
 */
@Configuration
@Slf4j
public class ShiroConfig {

    @Autowired
    SysAccessPermissionService sysAccessPermissionServiceImpl;

    @Autowired
    SecurityManager securityManager;

    @Autowired
    RoleOrAuthorizationFilter roleOrFilter;

    /**
     * 配置FilterFactoryBean
     * */
    @Bean(name = "myShiroFilter")
    public ShiroFilterFactoryBean myShiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置登录链接(前后端分离方案中这里不返回页面，返回403报文，供前端跳转到登录页面)
        shiroFilterFactoryBean.setLoginUrl("/403");
        // 登录成功后要跳转的链接(前后端分离方案这个不需要)
        //shiroFilterFactoryBean.setSuccessUrl("/home");
        // 未授权跳转链接;
        shiroFilterFactoryBean.setUnauthorizedUrl("/401");

        // 添加自定义过滤器
        shiroFilterFactoryBean.getFilters().put("roleOr",roleOrFilter);

        //拦截链配置
        Map<String, String> filterChainDefinitionMap = constructFilterChainDefinitionMap();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        log.info("ShiroFilterFactoryBean注入成功!");
        return shiroFilterFactoryBean;
    }

    /**
     * 构造shiro过滤链配置
     * */
    private Map<String,String> constructFilterChainDefinitionMap(){
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //动态加载url权限配置,从数据库获取
        List<SysAccessPermissionTest> list = sysAccessPermissionServiceImpl.getAll();
        for (SysAccessPermissionTest item : list) {
            filterChainDefinitionMap.put(item.getUrl(),item.getRoles());
        }

        return filterChainDefinitionMap;
    }

}
