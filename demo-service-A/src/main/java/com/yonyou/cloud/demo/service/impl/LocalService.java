package com.yonyou.cloud.demo.service.impl;

import com.yonyou.cloud.demo.service.GlobalService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName LocalService
 * @Description TODO
 * @Author scott
 * @Date 2021/1/26 11:40 上午
 * @Version 1.0
 **/
@Service
@Log4j2
public class LocalService {
    @Resource
    private GlobalService globalService;
    /**
     * 开启本地事务
     * 说明 全局事务回滚，抛出异常，本地事务方法捕捉异常，则会回滚本地事务
     */
    @Transactional(rollbackFor = Exception.class)
    public void startTransactional() {
        // 数据库操作 本地事务操作

        // 通过try 来感知全局事务方法抛出的异常 如果能够捕捉异常 那么就可以本地事务可以正常回滚
        try {
            globalService.startGlobalTransactional();
        } catch (Exception exception) {
            log.error("捕捉异常 异常信息 = {}", exception.getMessage());
        }
    }
}
