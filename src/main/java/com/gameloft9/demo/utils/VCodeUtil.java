package com.gameloft9.demo.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 验证码工具
 * Created by gameloft9 on 2017/12/6.
 */
public class VCodeUtil {

    private static final int WIDTH = 70;//验证码长度
    private static final int HEIGHT = 20;//验证码高度
    private static final int LENGTH = 4;//验证码位数

    /**
     * 获取验证码
     * */
    public static BufferedImage getVCodeImage(char[] rands){
        // 创建内存图象并获得其图形上下文
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        drawBackground(g);
        drawRands(g, rands);

        // 结束图像的绘制过程，完成图像
        g.dispose();

        return image;
    }

    /**
     * 生产随机码
     * */
    public static char[] generateVCode() {
        // 定义验证码的字符表
        String chars = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

        char[] rands = new char[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            int rand = (int) (Math.random() * chars.length());
            rands[i] = chars.charAt(rand);
        }

        return rands;
    }

    /**
     *  画出验证码
     * */
    private static Graphics drawRands(Graphics g, char[] rands) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));

        // 在不同的高度上输出验证码的每个字符
        g.drawString("" + rands[0], 1, 17);
        g.drawString("" + rands[1], 16, 15);
        g.drawString("" + rands[2], 31, 18);
        g.drawString("" + rands[3], 46, 16);

        return g;
    }

    /**
     * 画背景
     * */
    private static Graphics drawBackground(Graphics g) {
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 随机产生120个干扰点
        for (int i = 0; i < 120; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);

            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);

            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);

        }

        // 加两条干扰线
        g.drawLine(0, 5, 90, 5);
        g.drawLine(0, 15, 90, 15);

        return g;
    }
}
