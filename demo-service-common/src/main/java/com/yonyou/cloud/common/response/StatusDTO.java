package com.yonyou.cloud.common.response;

import com.yonyou.cloud.common.enums.ResultCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class StatusDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 接口响应状态码 -成功 */
    private static final ResultCodeEnum RESP_STATUS_CODE_SUCCESS = ResultCodeEnum.SUCCESS;
    /** 接口响应状态码 -失败 */
    private static final ResultCodeEnum RESP_STATUS_CODE_FAIL = ResultCodeEnum.FAIL;
    /** 接口成功响应提示信息 */
    private static final String SUCCESS_MSG = ResultCodeEnum.SUCCESS.getMessage();
    /** 接口失败响应提示信息 */
    private static final String FAIL_MSG = ResultCodeEnum.FAIL.getMessage();

    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return StringUtils.equals(this.code, RESP_STATUS_CODE_SUCCESS.getCode());
    }

    /**
     * 成功
     * @return 结果
     */
    public static StatusDTO<String> buildSuccess() {
        return buildSuccess(SUCCESS_MSG);
    }

    /**
     * 成功
     * @param message 指定的提示消息
     * @return 结果
     */
    public static StatusDTO<String> buildSuccess(String message) {
        return buildStatusWithCode(RESP_STATUS_CODE_SUCCESS, message);
    }

    /**
     * 成功
     * @param message 提示消息
     * @param data 指定的数据
     * @param <E> 数据类型
     * @return 结果
     */
    public static <E> StatusDTO<E> buildDataSuccess(String message, E data) {
        StatusDTO<E> dto = new StatusDTO<E>();
        dto.code = RESP_STATUS_CODE_SUCCESS.getCode();
        dto.message = message;
        dto.data = data;
        return dto;
    }

    /**
     * 成功
     * @param data 指定的数据
     * @param <E> 数据类型
     * @return 结果
     */
    public static <E> StatusDTO<E> buildDataSuccess(E data) {
        return buildDataSuccess(SUCCESS_MSG, data);
    }

    /**
     * 自定义成功的code
     * @param code 状态码
     * @param message 信息
     */
    public static StatusDTO<String> buildStatusWithCode(ResultCodeEnum code, String message) {
        StatusDTO<String> dto = new StatusDTO<String>();
        dto.code = code.getCode();
        dto.message = message;
        return dto;
    }

    /**
     * 失败
     * @return 结果
     */
    public static StatusDTO<String> buildFailure() {
        return buildFailureStatusDto(FAIL_MSG);
    }

    /**
     * 失败
     * @param message 指定的提示消息
     * @return 结果
     */
    public static StatusDTO<String> buildFailureStatusDto(String message) {
        return buildStatusWithCode(RESP_STATUS_CODE_FAIL, message);
    }

    /**
     * 失败
     * @param message 提示消息
     * @param <T> 携带的数据类型
     * @return 结果
     */
    public static <T> StatusDTO<T> buildFailure(String message) {
        StatusDTO<T> dto = new StatusDTO<T>();
        dto.setCode(RESP_STATUS_CODE_FAIL.getCode());
        dto.setMessage(message);
        return dto;
    }

    /**
     * 根根表达式返回数据
     * @param bool 真返回失败 假返回成功
     * @return 结果
     */
    public static <T> StatusDTO<T> buildWithExpressie(boolean bool) {
        if (bool) {
            return buildWithExpressie(bool, FAIL_MSG);
        }
        return buildWithExpressie(bool, SUCCESS_MSG);
    }

    /**
     * 根根表达式返回数据
     * @param bool 真返回失败 假返回成功
     * @param message 指定消息
     * @return 结果
     */
    public static <T> StatusDTO<T> buildWithExpressie(boolean bool, String message) {
        StatusDTO<T> dto = new StatusDTO<T>();
        if (bool) {
            dto.setCode(RESP_STATUS_CODE_FAIL.getCode());
        }
        dto.setCode(RESP_STATUS_CODE_SUCCESS.getCode());
        dto.setMessage(message);
        return dto;
    }

    /**
     * 失败
     * @param message 指定的提示信息
     * @param t 指定的数据
     * @param <T> 数据类型
     * @return 结果
     */
    public static <T> StatusDTO<T> buildFailure(String message, T t) {
        StatusDTO<T> dto = new StatusDTO<T>();
        dto.code = RESP_STATUS_CODE_FAIL.getCode();
        dto.data = t;
        dto.message = message;
        return dto;
    }

    /**
     * 失败
     * @param code 指定状态码
     * @param message 指定的提示消息
     * @param t 携带的数据
     * @param <T> 数据类型
     * @return 结果
     */
    public static <T> StatusDTO<T> buildFailure(String code, String message, T t) {
        StatusDTO<T> dto = new StatusDTO<T>();
        dto.code = RESP_STATUS_CODE_FAIL.getCode();
        dto.message = message;
        dto.code = code;
        return dto;
    }
}
