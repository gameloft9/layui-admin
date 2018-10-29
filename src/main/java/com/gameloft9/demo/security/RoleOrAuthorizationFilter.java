package com.gameloft9.demo.security;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * shiro的url定义中的roles参数，必须满足所有角色才通过(也就是and关系)
 * 这里自定义一个权限满足角色之一的Or关系的filter
 * Created by gameloft9 on 2018/7/23.
 */
public class RoleOrAuthorizationFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }

        for(int i=0;i<rolesArray.length;i++) {
            if(subject.hasRole(rolesArray[i])) { // 有一个满足即可
                return true;
            }
        }

        return false;
    }
}
