package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.UserMapper;
import com.gameloft9.demo.dataaccess.model.system.UserTest;
import com.gameloft9.demo.mgrframework.beans.response.AbstractResult;
import com.gameloft9.demo.mgrframework.exceptions.BizException;
import com.gameloft9.demo.service.api.system.LoginService;
import com.gameloft9.demo.service.beans.system.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.gameloft9.demo.mgrframework.utils.CheckUtil.*;

/**
 * 登录服务
 * Created by gameloft9 on 2017/12/5.
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    UserMapper userMapper;

    /**
     * 登录
     * @param loginName 登录名
     * @param pwd 密码
     * */
    public LoginResponse login(String loginName, String pwd, String code){
        LoginResponse loginResponse = new LoginResponse();
        //参数校验
        notBlank(code,"验证码为空");
        notBlank(loginName,"用户名为空");
        notBlank(pwd,"密码为空");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //验证验证码
        String vcode =  request.getSession().getAttribute("vcode").toString();
        check(code.equalsIgnoreCase(vcode),"验证码错误");

        //当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //获取基于用户名和密码的令牌(生产环境下密码需要转换为加密后的密码)
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, pwd);
        try {
            //提交认证，会调用Realm的doGetAuthenticationInfo，进行认证
            currentUser.login(token);
        } catch(UnknownAccountException e){
            log.info("用户不存在");
            throw new BizException(AbstractResult.BIZ_FAIL,"用户不存在");
        }catch(IncorrectCredentialsException e){
            log.info("用户名或密码错误");
            throw new BizException(AbstractResult.BIZ_FAIL,"用户名或密码错误");

        }catch (AuthenticationException e) {
            log.error("认证异常",e);
            throw new BizException(AbstractResult.BIZ_FAIL,"认证异常");
        }

        //验证是否通过
        if(currentUser.isAuthenticated()){
            log.info("验证成功！");
            //还可以把用户信息放入session中
            request.getSession().setAttribute("sysUser",loginName);

            //拼接返回信息
            UserTest userTest = userMapper.getByLoginName(loginName);
            loginResponse.setUserId(userTest.getId());
            loginResponse.setLoginName(loginName);
            String baseUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
            loginResponse.setWebContext(baseUrl);
            return loginResponse;
        }

        log.info("用户名或密码错误,name:{},pwd:{}",loginName,pwd);
        throw new BizException(AbstractResult.BIZ_FAIL,"用户名或密码错误");
    }

    /**
     * 登出并清理session
     * */
    public String logout(){
        SecurityUtils.getSubject().logout();//登出
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().removeAttribute("sysUser");//清理session
        return null;
    }
}
