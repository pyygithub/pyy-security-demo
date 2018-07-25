package com.pyy.common.exception;

import lombok.Data;

/**
 * 自定义异常
 * @author: pyygithub
 * @date: 2018-07-22 10:59
 * @version: v1.0
 */
@Data
public class PyyException extends RuntimeException{

    private int code = 500;
    private String msg;

    public PyyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public PyyException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
