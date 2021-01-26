package com.yonyou.cloud.common.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import org.apache.commons.lang3.StringUtils;

/**
 * FeignClient 拦截
 */
public class SeataFeignClientInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String xid = RootContext.getXID();
        if (!StringUtils.isEmpty(xid)){
            requestTemplate.header(RootContext.KEY_XID,xid);
        }
    }
}
