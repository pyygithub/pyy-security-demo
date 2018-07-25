package com.pyy.security.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义验证码 异常
 * @author: pyygithub
 * @date: 2018-07-25 15:07
 * @version: v1.0
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
