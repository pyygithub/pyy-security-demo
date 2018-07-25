package com.pyy.security.browser.controller;

import com.pyy.common.enums.ResultEnum;
import com.pyy.modules.demo.vo.ResultVO;
import com.pyy.security.browser.config.BrowserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: pyygithub
 * @date: 2018-07-24 14:54
 * @version: v1.0
 */
@RestController
@Slf4j
public class BrowserSecurityController {

    // 请求的缓存对象
    private RequestCache requestCache = new HttpSessionRequestCache();
    // 跳转工具类
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private BrowserProperties browserProperties;

    /**
     * 当需要身份认证时，跳转到此请求
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/authentication/require")
    public ResultVO requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("身份验证处理");
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String target = savedRequest.getRedirectUrl();//引发跳转的url
            log.info("【身份认证】引发跳转的URL：{}", target);
            if (StringUtils.endsWithIgnoreCase(target, ".html")) {//如果引发跳转的url后缀为html，则跳转到html登陆页面
                //跳转到自定义配置的登陆页面
                redirectStrategy.sendRedirect(request, response, browserProperties.getLoginPage());
            }
        }
        return new ResultVO(ResultEnum.UNAUTHORIZED);
    }

}
