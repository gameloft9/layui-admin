package com.gameloft9.demo.mgrframework.annotation;

import com.gameloft9.demo.mgrframework.beans.constant.OperType;

import java.lang.annotation.*;

/**
 * 业务操作注解
 * Created by gameloft9 on 2017/11/13.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BizOperLog {
    /**操作类型*/
    OperType operType() default OperType.Query;

    /**备注*/
    String memo() default "";
}
