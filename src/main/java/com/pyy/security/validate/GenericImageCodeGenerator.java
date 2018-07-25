package com.pyy.security.validate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 图形验证码默认实现类
 * @author: pyygithub
 * @date: 2018-07-25 19:16
 * @version: v1.0
 */
@Slf4j
public class GenericImageCodeGenerator implements ImageCodeGenerator{

    private ImageCodeProperties imageCodeProperties;

    @Override
    /** 生成验证码方法 */
    public ImageCode createImageCode(HttpServletRequest request) {
        // 先从请求参数中获取宽度和高度，如果没有使用配置文件配置值  /code/image?width=100&height=20
        int width = ServletRequestUtils.getIntParameter(request,"width",imageCodeProperties.getWidth());
        int height = ServletRequestUtils.getIntParameter(request,"height",imageCodeProperties.getHeight());
        int length = imageCodeProperties.getLength();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();
        log.info("【系统生成验证码】code={}", sRand);

        return new ImageCode(image, sRand, imageCodeProperties.getExpireIn());
    }


    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public ImageCodeProperties getImageCodeProperties() {
        return imageCodeProperties;
    }

    public void setImageCodeProperties(ImageCodeProperties imageCodeProperties) {
        this.imageCodeProperties = imageCodeProperties;
    }
}
