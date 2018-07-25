package com.pyy.security.validate;

import com.pyy.security.browser.config.BrowserProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 验证码过滤器
 *  OncePerRequestFilter： 确保在一次请求只通过一次filter，而不需要重复执行。
 * @author: pyygithub
 * @date: 2018-07-25 15:01
 * @version: v1.0
 */
@Slf4j
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private BrowserProperties browserProperties;

    @Autowired
    private ImageCodeProperties imageCodeProperties;

    // 需要做验证码验证逻辑的地址
    private Set<String> urls = new HashSet<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparator(imageCodeProperties.getUrl(), ",");
        for (String configUrl : configUrls) {
            urls.add(configUrl);
            urls.add(browserProperties.getLoginHandlerUrl());
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 判断地址是否需要执行验证操作
        boolean action = false;
        for(String url : urls) {
            if(antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }
        //判断需要执行图形验证码地址进行验证码校验
        if (action) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                log.error(e.getMessage());
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                // 抛出异常后不继续Filter，直接返回掉
                return;
            }

        }
        filterChain.doFilter(request, response);
    }

    /** 验证码校验逻辑*/
    private void validate(ServletWebRequest request) {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
        String codeInRequest = "";
        try {
            // 获取请求参数中的验证码值
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpried()) {
            // 从session中移除之前的验证码
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        // 验证流程执行完毕后，需要从session中移除之前的验证码
        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
    }

}
