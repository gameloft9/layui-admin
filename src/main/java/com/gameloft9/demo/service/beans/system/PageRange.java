package com.gameloft9.demo.service.beans.system;

import lombok.Data;

/**
 * 分页范围
 * Created by gameloft9 on 2017/12/11.
 */
@Data
public class PageRange {

    /**
     * 页序
     * */
    private int page;

    /**
     * 分页大小
     * */
    private int limit;

    public PageRange(int page,int limit){
        this.page = page;
        this.limit = limit;
    }

    public PageRange(String page,String limit){
        this.page = Integer.parseInt(page);
        this.limit = Integer.parseInt(limit);;
    }

    /**
     * 获取开始序号
     * */
    public int getStart(){
        int start = (page - 1) * limit;
        return start;
    }

    /**
     * 获取结束序号
     * */
    public int getEnd(){
        int end = limit * page + 1;
        return end;
    }

}
