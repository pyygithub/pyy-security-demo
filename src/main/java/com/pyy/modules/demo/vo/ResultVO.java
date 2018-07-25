package com.pyy.modules.demo.vo;


import com.pyy.common.enums.ResultEnum;

/**
 * 结果集
 * @author: pyygithub
 * @date: 2018-07-22 11:30
 * @version: v1.0
 */
public class ResultVO<T> {
    private int code;
    private String msg;
    private T data;

    public ResultVO(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getReasonPhrase();
    }

    public ResultVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultVO ok() {
        return new ResultVO(ResultEnum.OK);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
