package com.yonyou.cloud.common.response;

import java.io.Serializable;

/**
 * @ClassName ResponseDTO
 * @Description TODO
 * @Author scott
 * @Date 2020/10/20 4:21 下午
 * @Version 1.0
 **/
public class ResponseDTO<T> implements Serializable {

    private String returnCode;

    private String returnMessage;

    private String cause;

    private T data;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" + "returnCode='" + returnCode + '\'' + ", returnMessage='" + returnMessage + '\''
                + ", data=" + data + '}';
    }

    /**
     * @return the cause
     */
    public String getCause() {
        return cause;
    }

    /**
     * @param cause the cause to set
     */
    public void setCause(String cause) {
        this.cause = cause;
    }


}
