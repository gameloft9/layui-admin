package com.gameloft9.demo.mgrframework.beans.constant;

/**
 * 操作类型枚举
 * Created by leiYao on 2017/11/13.
 */
public enum OperType {
    Query("sel"),//查询，一般不用，仅作为默认项
    ADD("add"),//新增操作
    DELETE("del"),//删除操作
    UPDATE("upd"),//更新操作
    UPLOAD("uploadFile"),//上传文件
    DOWNLOAD("downloadFile");//下载文件

    private String value;

    private OperType(String type){
        this.value = type;
    }

    public String getValue(){
        return this.value;
    }
}
