package com.pyy.security.browser.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: pyygithub
 * @date: 2018-07-24 16:36
 * @version: v1.0
 */
@Configuration
@ConfigurationProperties(prefix = "pyysecurity.browser")
public class BrowserProperties {
    /**
     * 登录页面地址
     */
    private String loginPage = "/signIn.html";

    /**
     * 登录后台操作地址
     */
    private String loginHandlerUrl = "/authentication/login";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getLoginHandlerUrl() {
        return loginHandlerUrl;
    }

    public void setLoginHandlerUrl(String loginHandlerUrl) {
        this.loginHandlerUrl = loginHandlerUrl;
    }
}
