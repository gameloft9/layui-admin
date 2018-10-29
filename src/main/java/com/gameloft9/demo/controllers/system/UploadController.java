package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.FileUtil;
import com.gameloft9.demo.utils.PropertyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by gameloft9 on 2017/12/28.
 */
@Controller
@Slf4j
public class UploadController {


    /**
     * 上传文件
     * @param file 上传文件
     * @param type 文件业务类型 "userFace"-用户头像
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public IResult upload(MultipartFile file, String type, String fileName) throws Exception {
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(FileUtil.saveAndReturnUrl(file.getBytes(), Constants.AttachmentType.USER_FACE.value,fileName, PropertyUtil.getProperty("web_base_url"),PropertyUtil.getProperty("file_root_path")));
    }
}
