package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.service.api.system.SysOperLogService;
import com.gameloft9.demo.dataaccess.dao.system.SysOperLogTestMapper;
import com.gameloft9.demo.dataaccess.model.system.SysOperLogTest;
import com.gameloft9.demo.service.beans.system.LogBatchDelRequest;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.DateUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by gameloft9 on 2017/12/5.
 */
@Service
@Slf4j
public class SysOperLogServiceImpl implements SysOperLogService{

    @Autowired
    SysOperLogTestMapper sysOperLogMapper;

    /**
     * 插入应用访问日志
     * @param userId 用户id
     * @param loginName 登录名
     * @param ipAddr ip地址
     * @param operType 操作类型
     * @param memo 描述
     * */
    public int insertOperLog(String userId,String loginName,String ipAddr,String operType,String memo){
        SysOperLogTest log = new SysOperLogTest();
        log.setId(UUIDUtil.getUUID());
        log.setCreateDate(new Date());
        log.setUserId(userId);
        log.setIpAddr(ipAddr);
        log.setLoginName(loginName);
        log.setOperType(operType);
        log.setMemo(memo);
        return sysOperLogMapper.insertSelective(log);
    }

    /**
     * 获取所有日志
     * @param page 页序
     * @param limit 分页大小
     * @param loginName 登录名
     * @param operType 操作类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * */
    public List<SysOperLogTest> getAll(String page, String limit, String loginName, String operType, String startTime, String endTime){
        PageRange pageRange = new PageRange(page,limit);

        //解析日期
        Date startDate = null,endDate=null;
        if(StringUtils.isNotBlank(startTime)){
            startDate = DateUtil.str2Date(startTime);
        }
        if(StringUtils.isNotBlank(endTime)){
            endDate = DateUtil.str2Date(endTime);
        }

        return sysOperLogMapper.selectAll(pageRange.getStart(),pageRange.getEnd(),loginName,operType,startDate,endDate);
    }

    /**
     * 获取所有日志个数
     * @param loginName 登录名
     * @param operType 操作类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * */
    public int countGetAll(String loginName, String operType, String startTime, String endTime){
        //解析日期
        Date startDate = null,endDate=null;
        if(StringUtils.isNotBlank(startTime)){
            startDate = DateUtil.str2Date(startTime);
        }
        if(StringUtils.isNotBlank(endTime)){
            endDate = DateUtil.str2Date(endTime);
        }
        return sysOperLogMapper.countSelectAll(loginName,operType,startDate,endDate);
    }

    /**
     * 根据id删除记录
     * @param id 日志id
     * */
    public boolean delete(String id){
        CheckUtil.notBlank(id,"日志id为空");

        sysOperLogMapper.deleteByPrimaryKey(id);
        return true;
    }

    /**
     * 批量删除日志
     * @param request 请求
     * */
    public boolean batchDelete(LogBatchDelRequest request){
        CheckUtil.notNull(request,"批量删除请求参数为空");

        Date startDate = null;
        Date endDate =  null;
        if(StringUtils.isNotBlank(request.getStartTime())){
            startDate = DateUtil.str2Date(request.getStartTime());
        }
        if(StringUtils.isNotBlank(request.getEndTime())){
            endDate = DateUtil.str2Date(request.getEndTime());
        }


        if(request.getAllDel()){
            log.info("删除全部查询出来的记录");
            return sysOperLogMapper.deleteByQuery(request.getLoginName(),request.getOperType(),startDate,endDate);
        }

        if(request.getNotDelIds().size() == 0){
            log.info("仅删除勾选记录");
            return sysOperLogMapper.deleteByList(request.getDelIds());
        }

        log.info("删除全部查询出来的，仅保留未勾选记录");
        return sysOperLogMapper.deleteByMap(request.getLoginName(),request.getOperType(),startDate,endDate,request.getNotDelIds());
    }

}
