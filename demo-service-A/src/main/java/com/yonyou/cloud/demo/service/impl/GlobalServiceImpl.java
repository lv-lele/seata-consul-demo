package com.yonyou.cloud.demo.service.impl;

import com.yonyou.cloud.demo.feign.ServiceBFeignClient;
import com.yonyou.cloud.demo.req.RequestDto;
import com.yonyou.cloud.demo.service.DemoServiceA;
import com.yonyou.cloud.demo.service.GlobalService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName GlobalService
 * @Description TODO
 * @Author scott
 * @Date 2021/1/26 10:38 上午
 * @Version 1.0
 **/
@Service
@Log4j2
public class GlobalServiceImpl implements GlobalService {
    @Resource
    private ServiceBFeignClient serviceBFeignClient;
    @Resource
    private DemoServiceA demoServiceA;
    @Resource
    private GlobalService globalService;

    /**
     * 开启全局事务
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class, timeoutMills = 60 * 1000)
    public void startGlobalTransactional() {
        RequestDto requestDtoA = new RequestDto(System.currentTimeMillis(), "我是demo-service-A");
        // demo-service-A 注册分支事务A insert
        demoServiceA.insert(null, requestDtoA);
        RequestDto requestDtoB = new RequestDto(System.currentTimeMillis(), "我是demo-service-B");
        // demo-service-B 注册分支事务B update
        serviceBFeignClient.requestBforUpdate(requestDtoB);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startTransactional() {
        try {
            startGlobalTransactional();
        } catch (Exception exception) {
            log.error("捕捉异常 异常信息 = {}", exception.getMessage());
        }
    }
}
