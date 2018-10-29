package com.gameloft9.demo.service.beans.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 批量删除日期请求bean
 * Created by gameloft9 on 2017/12/18.
 */
@Data
public class LogBatchDelRequest implements Serializable{

    /**是否全部删除*/
    private Boolean allDel;
    /**待删除id列表-全部删除时无效*/
    private List<String> delIds;
    /**非删除id列表-仅全部删除时有效*/
    private List<String> notDelIds;
    /**登录名-仅全部删除时有效*/
    private String loginName;
    /**操作类型-仅全部删除时有效*/
    private String operType;
    /**开始时间-仅全部删除时有效*/
    private String startTime;
    /**结束时间-仅全部删除时有效*/
    private String endTime;
}
