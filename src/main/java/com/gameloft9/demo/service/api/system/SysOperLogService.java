package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysOperLogTest;
import com.gameloft9.demo.service.beans.system.LogBatchDelRequest;

import java.util.List;

/**
 * 应用日志
 * Created by gameloft9 on 2017/12/5.
 */
public interface SysOperLogService {

    /**
     * 插入应用访问日志
     * @param userId 用户id
     * @param loginName 登录名
     * @param ipAddr ip地址
     * @param operType 操作类型
     * @param memo 描述
     * */
    int insertOperLog(String userId,String loginName,String ipAddr,String operType,String memo);

    /**
     * 获取所有日志
     * @param page 页序
     * @param limit 分页大小
     * @param loginName 登录名
     * @param operType 操作类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * */
    List<SysOperLogTest> getAll(String page, String limit, String loginName, String operType, String startTime, String endTime);

    /**
     * 获取所有日志个数
     * @param loginName 登录名
     * @param operType 操作类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * */
    int countGetAll(String loginName, String operType, String startTime, String endTime);

    /**
     * 根据id删除记录
     * @param id 日志id
     * */
    boolean delete(String id);

    /**
     * 批量删除日志
     * @param request 请求
     * */
    boolean batchDelete(LogBatchDelRequest request);
}
