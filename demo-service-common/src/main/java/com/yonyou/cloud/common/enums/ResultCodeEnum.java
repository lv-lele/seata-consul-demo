package com.yonyou.cloud.common.enums;

import lombok.Getter;

/**
 * 成功失败状态码枚举
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS("200", "成功"),
    FAIL("999", "失败"),
    EXCEPTION("500", "系统异常");

    private final String code;
    private final String message;

    private ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
