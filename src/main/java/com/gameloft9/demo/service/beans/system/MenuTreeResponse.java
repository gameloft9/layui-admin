package com.gameloft9.demo.service.beans.system;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 返回给layui前端的菜单树
 * Created by gameloft9 on 2017/12/7.
 */
@Data
public class MenuTreeResponse implements Serializable{

    private String id;
    /**标题*/
    private String title;
    /**链接*/
    private String href;
    /**图标名称*/
    private String icon;
    /**目标窗口*/
    private String target;
    /**是否展开*/
    private Boolean spread;
    /**子菜单*/
    private List<MenuTreeResponse> children;

    /**
     * 构造函数
     * */
    public MenuTreeResponse(){
        this.children = new LinkedList<MenuTreeResponse>();
    }
}
