package com.pyy.common.exception;


import com.pyy.common.enums.ResultEnum;
import com.pyy.modules.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * @author: pyygithub
 * @date: 2018-07-22 11:28
 * @version: v1.0
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResultVO handleRRException(Exception e){
        if(e instanceof PyyException) {
            PyyException pyyException = (PyyException) e;
            return new ResultVO(pyyException.getCode(), pyyException.getMsg());
        }else {
            return new ResultVO(ResultEnum.INTERNAL_SERVER_ERROR);
        }
    }
}
