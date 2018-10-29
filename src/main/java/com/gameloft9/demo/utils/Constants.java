package com.gameloft9.demo.utils;

import lombok.Data;

/**
 * 常量
 * Created by gameloft9 on 2017/12/13.
 */
public class Constants {

    /**
     * 菜单类型
     * */
    public enum MenuType{
        FIRST_CLASS("1","一级菜单"),
        SECOND_CLASS("2","二级菜单");

        public String value;
        public String name;

        MenuType(String value,String name){
            this.value = value;
            this.name = name;
        }
    }

    /**
     * 菜单图标类型,具体值请参考阿里iconfont
     * */
    public enum MenuIcon{

        SHE_ZHI("icon-shezhi1","设置"),
        ZHANG_HU("icon-zhanghu","账户"),
        BIAN_JI("icon-edit","编辑"),
        GONG_GAO("icon-gonggao","公告"),
        WEN_BEN("icon-text","文本");

        public String value;
        public String name;

        MenuIcon(String value,String name){
            this.value = value;
            this.name = name;
        }
    }

    /**
     * 上传附件业务类型
     * */
    public enum AttachmentType{

        USER_FACE("userFace");//用户头像

        public String value;

        AttachmentType(String name){
            this.value = name;
        }
    }

    /**
     * 一级菜单初始CODE
     * */
    public static final String INIT_FIRST_CLASS_MENU_CODE = "1";

    /**
     * 二级菜单初始CODE
     * */
    public static final String INIT_SECOND_CLASS_MENU_CODE = "1";

    /**
     * 组织机构编码规则
     * */
    public static class OrgCodeRule{
        /**根机构编码*/
        public static final String ROOT_CODE = "010001";
        /**初始序号*/
        public static final String INIT_SORT = "0001";
        /**初始前缀*/
        public static final String INIT_LEVEL = "01";
    }

    /**
     * 初始化密码
     * */
    public static final String INIT_PWD = "123456";

}
