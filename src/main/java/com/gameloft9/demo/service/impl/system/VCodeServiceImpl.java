package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.service.api.system.VCodeService;
import com.gameloft9.demo.utils.VCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * 验证码服务
 * Created by gameloft9 on 2017/12/6.
 */
@Slf4j
@Service
public class VCodeServiceImpl implements VCodeService{

    /**
     * 输出验证码
     * */
    public void outPutVCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        response.setContentType("image/jpeg");

        ServletOutputStream sos = response.getOutputStream();

        // 设置浏览器不要缓存此图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //创建内存图象
        char[] vCode = VCodeUtil.generateVCode();
        BufferedImage image = VCodeUtil.getVCodeImage(vCode);
        // 将图像输出到客户端
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", bos);

        byte[] buf = bos.toByteArray();
        response.setContentLength(buf.length);

        // 下面的语句也可写成：bos.writeTo(sos);
        sos.write(buf);
        bos.close();
        sos.close();

        // 将当前验证码存入到Session中
        session.setAttribute("vcode", new String(vCode));
    }
}
