package com.pyy.security.validate;

import javax.servlet.http.HttpServletRequest;

/**
 * 图形验证码接口
 * @author: pyygithub
 * @date: 2018-07-25 19:13
 * @version: v1.0
 */
public interface ImageCodeGenerator {

    ImageCode createImageCode(HttpServletRequest request);
}
