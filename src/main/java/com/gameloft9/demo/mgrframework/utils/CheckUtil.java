package com.gameloft9.demo.mgrframework.utils;

import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.mgrframework.exceptions.CheckException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.*;
import java.util.LinkedList;
import java.util.List;


/**
 * 通用校验工具类
 * 1、一级校验：校验model上面的注解
 * 2、二级校验：service里面的通用检查
 *
 * @author gameloft9 2017-11-10
 */
@Slf4j
public class CheckUtil {

    /**
     * 条件检查
     *
     * @param condition 检查条件，如果为false则抛异常
     * @param errMsg    错误信息
     * @param args      参数列表
     */
    public static void check(boolean condition, String errMsg, Object... args) {
        if (!condition) {
            fail(errMsg, args);
        }
    }

    /**
     * 不能为empty
     *
     * @param str    待检查字符串
     * @param errMsg 错误信息
     * @param args   参数列表
     */
    public static void notEmpty(String str, String errMsg, Object... args) {
        if (str == null || str.isEmpty()) {
            fail(errMsg, args);
        }
    }

    /**
     * 不能为blank
     *
     * @param str    待检查字符串
     * @param errMsg 错误信息
     * @param args   参数列表
     */
    public static void notBlank(String str, String errMsg, Object... args) {
        if (StringUtils.isBlank(str)) {
            fail(errMsg, args);
        }
    }

    /**
     * 不能为Null
     *
     * @param obj    待检查对象
     * @param errMsg 错误信息
     * @param args   参数列表
     */
    public static void notNull(Object obj, String errMsg, Object... args) {
        if (obj == null) {
            fail(errMsg, args);
        }
    }

    /**
     * 校验model上的注解
     *
     * @param pjp 连接点
     */
    public static void checkModel(ProceedingJoinPoint pjp) {
        StringBuilder sb = new StringBuilder();
        try {
            //找到BindingResult参数
            List<BindingResult> results = getBindingResult(pjp);
            if (results != null && !results.isEmpty()) {
                for (BindingResult result : results) {
                    if (null != result && result.hasErrors()) {
                        //拼接错误信息
                        if (null != result.getFieldErrors()) {
                            for (FieldError fe : result.getFieldErrors()) {
                                sb.append(fe.getField() + "-" + fe.getDefaultMessage()).append(" ");
                            }
                        }
                    }
                }

                if (StringUtils.isNotBlank(sb.toString())) {
                    fail(sb.toString());//抛出检查异常
                }
            }
        } catch (Exception e) {
            fail(e.getMessage());//抛出检查异常
        }
    }

    /****************************私有方法区******************************************/
    /**
     * 失败抛出异常
     *
     * @param errMsg 错误信息
     * @param args   参数列表
     */
    private static void fail(String errMsg, Object... args) {
        StringBuilder sb = new StringBuilder();
        if (args == null || args.length <= 0) {//没有错误参数直接抛错误信息
            throw new CheckException(ResultBean.CHECK_FAIL, errMsg);
        }

        //有错误参数,拼接错误信息
        sb.append(errMsg + ",");
        for (Object item : args) {
            sb.append(item.toString() + " ");
        }
        throw new CheckException(ResultBean.CHECK_FAIL, sb.toString());
    }

    /**
     * 拿到BindResult
     *
     * @param pjp 连接点
     */
    private static List<BindingResult> getBindingResult(ProceedingJoinPoint pjp) throws Exception {
        List<BindingResult> results = new LinkedList<BindingResult>();

        //拿到controller class，method
        Class<?> clazz = pjp.getTarget().getClass();
        String clazzName = clazz.getName();//类完整限定名
        String methodName = pjp.getSignature().getName();//方法名

        //找到该方法对象
        Method targetMethod = null;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                targetMethod = method;
            }
        }
        if (targetMethod == null) {
            return null;
        }

        //找到BindResult类型
        List<Integer> indexs = new LinkedList<Integer>();
        Class<?> clazzs[] = targetMethod.getParameterTypes();
        for (int i = 0; i < clazzs.length; i++) {
            if (clazzs[i].getName().equals("org.springframework.validation.BindingResult")) {
                indexs.add(i);
            }
        }

        //返回BindingResult
        if (indexs != null && !indexs.isEmpty()) {
            for (Integer i : indexs) {
                results.add((BindingResult) pjp.getArgs()[i]);
            }

            return results;
        }

        return null;
    }

}
