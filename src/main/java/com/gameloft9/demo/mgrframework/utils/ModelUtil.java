package com.gameloft9.demo.mgrframework.utils;

import com.gameloft9.demo.mgrframework.beans.modelview.ModelView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;

/**
 * Model工具类
 * Created by leiYao on 2017/11/13.
 */
@Slf4j
public class ModelUtil {

    /**
     * 填充Model
     * @param model Model
     * @param resBean 待填充的信息
     * */
    public static String fillModelAndReturnView(Model model,ModelView resBean){
          log.info("View Model填入参数:{}",resBean);
          //填充model并返回视图
          if(!resBean.getAttributes().isEmpty()){
              model.addAllAttributes(resBean.getAttributes());
          }

          return resBean.getReturnView();
    }
}
