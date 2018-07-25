package com.pyy.security.browser.authentication;

import com.alibaba.fastjson.JSON;
import com.pyy.common.enums.ResultEnum;
import com.pyy.modules.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证成功处理器
 * @author: pyygithub
 * @date: 2018-07-24 19:53
 * @version: v1.0
 */
@Component("pyyAuthenticationFailureHandler")
@Slf4j
public class PyyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("【用户认证】登录失败,e={}",e.getMessage());
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(new ResultVO(21,e.getMessage())));
    }
}
