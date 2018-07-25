package com.pyy.security.browser.config;

import com.pyy.security.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 浏览器Security配置类
 * @author: pyygithub
 * @date: 2018-07-22 15:24
 * @version: v1.0
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BrowserProperties browserProperties;

    @Autowired
    @Qualifier("pyyAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    @Qualifier("pyyAuthenticationFailureHandler")
    private AuthenticationFailureHandler pyyAuthenticationFailureHandler;

    @Autowired
    @Qualifier("validateCodeFilter")
    private ValidateCodeFilter validateCodeFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 添加验证码过滤器到用户名密码验证过滤器前面
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                // 当检测到请求需要认证的时候，跳转到我们的自定义控制器上
                .loginPage("/authentication/require")
                // 处理登录请求的地址
                .loginProcessingUrl(browserProperties.getLoginHandlerUrl())

                // 配置认证成功后处理器
                .successHandler(authenticationSuccessHandler)
                // 配置认证失败后处理器
                .failureHandler(pyyAuthenticationFailureHandler)

                .and()
                .authorizeRequests()
                .antMatchers(
                        "/authentication/require",
                        "/code/image",
                        browserProperties.getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()

                // 忽略CSRF认证，是Spring Boot Security默认配置的一个安全项（跨站请求防护），暂时关闭
                .and()
                .csrf()
                .disable();
    }
}
