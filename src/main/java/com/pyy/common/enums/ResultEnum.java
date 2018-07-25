package com.pyy.common.enums;

/**
 * @author: pyygithub
 * @date: 2018-07-22 11:05
 * @version: v1.0
 */
public enum ResultEnum {
    OK(200, "OK"),
    BAD_REQUEST(400, "参数错误"),
    UNAUTHORIZED(401, "未授权，请引导用户登录"),
    FORBIDDEN(403, "没有权限访问"),
    NOT_FOUND(404, "访问地址不存在"),
    METHOD_NOT_ALLOWED(405, "请求方式错误"),
    INTERNAL_SERVER_ERROR(500, "未知异常，请联系管理员"),
    LOGIN_FAILURE(21, "用户名或密码错误"),
    LOGIN_SUCCESS(10, "登录成功");


    private  int code;
    private  String reasonPhrase;

    ResultEnum(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }
}
