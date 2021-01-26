package com.yonyou.cloud.demo.service;

/**
 * @ClassName GlobalService
 * @Description TODO
 * @Author scott
 * @Date 2021/1/26 5:52 下午
 * @Version 1.0
 **/
public interface GlobalService {
    /**
     * 开启全局事务
     */
    void startGlobalTransactional();

    /**
     * 开启本地事务
     */
    void startTransactional();
}
