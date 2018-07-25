package com.pyy.security.browser.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: pyygithub
 * @date: 2018-07-24 16:36
 * @version: v1.0
 */
@Configuration
@ConfigurationProperties(prefix = "pyysecurity.browser")
@Setter
@Getter
public class BrowserProperties {
    /** 登录页面地址*/
    private String loginPage = "/signIn.html";

    /** 登录后台操作地址*/
    private String loginHandlerUrl = "/authentication/login";

    /** token失效 时间*/
    private int tokenValiditySeconds = 3600;


}
