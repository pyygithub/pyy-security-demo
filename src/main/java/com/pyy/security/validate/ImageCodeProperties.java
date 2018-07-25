package com.pyy.security.validate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: pyygithub
 * @date: 2018-07-25 18:45
 * @version: v1.0
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "pyysecurity.image")
public class ImageCodeProperties {
    /** 验证码宽度 */
    private int width = 67;
    /** 验证码宽度 */
    private int height = 23;
    /** 验证码长度 */
    private int length = 4;
    /** 验证码失效时间 */
    private int expireIn = 60;

    private String url;// 需要做图形验证码验证的 地址
}
