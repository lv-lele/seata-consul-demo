package com.yonyou.cloud.common.exception;

import com.yonyou.cloud.common.enums.ResultCodeEnum;
import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class DefaultException extends RuntimeException {
    private ResultCodeEnum resultCodeEnum;

    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.resultCodeEnum = resultCodeEnum;
    }

    public DefaultException(ResultCodeEnum resultCodeEnum, String message) {
        super(message);
        this.resultCodeEnum = resultCodeEnum;
    }

    public DefaultException(String message) {
        super(message);
    }

    public DefaultException(ResultCodeEnum resultCodeEnum, Throwable cause) {
        super(resultCodeEnum.getMessage(), cause);
        this.resultCodeEnum = resultCodeEnum;
    }
}
