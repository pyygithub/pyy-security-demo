package com.pyy.security.validate;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author: pyygithub
 * @date: 2018-07-25 14:50
 * @version: v1.0
 */
@Data
public class ImageCode {

    //图片
    private BufferedImage image;
    //验证码
    private String code;
    //过期时间点
    private LocalDateTime expireTime;

    /**
     * 判断验证码是否过期
     * @return
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    /**
     * 一般使用这个构造函数
     *
     * @param image
     * @param code
     * @param expireIn 过期秒数
     */
    public ImageCode(BufferedImage image, String code, long expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }
}
