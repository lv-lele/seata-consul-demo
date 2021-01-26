package com.yonyou.cloud.common.aop;

import com.yonyou.cloud.common.exception.DefaultException;
import com.yonyou.cloud.common.response.StatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
//@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public StatusDTO exceptionHandler(Exception e) {
        log.error("系统异常, 异常内容如下 --> ", e);
        return StatusDTO.buildFailure();
    }

    @ExceptionHandler(DefaultException.class)
    @ResponseBody
    public StatusDTO defaultException(DefaultException e) {
        log.warn("系统抛出自定义异常, 异常内容如下 --> ", e);
        return StatusDTO.buildFailure(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public StatusDTO argumentException(IllegalArgumentException e) {
        log.warn("抛出校验异常, 异常内容如下 --> ", e);
        return StatusDTO.buildFailure(e.getMessage());
    }
}
