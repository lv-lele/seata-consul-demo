package com.yonyou.cloud.common.interceptors;

import io.seata.core.context.RootContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

public class SeataRestTemplateInterceptor implements ClientHttpRequestInterceptor {
    /**
     * RestTemplate请求拦截器
     * 在头部设置全局事务ID
     *
     * @param request   request
     * @param body      书序
     * @param execution ClientHttpRequestExecution
     * @return ClientHttpResponse
     * @throws IOException 异常
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        String xid = RootContext.getXID();
        if (!StringUtils.isEmpty(xid)) {
            requestWrapper.getHeaders().add(RootContext.KEY_XID, xid);
        }
        return execution.execute(requestWrapper, body);
    }
}
