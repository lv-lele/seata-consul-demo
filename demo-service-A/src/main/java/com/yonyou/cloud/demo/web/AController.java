package com.yonyou.cloud.demo.web;

import com.yonyou.cloud.common.response.StatusDTO;
import com.yonyou.cloud.demo.service.GlobalService;
import com.yonyou.cloud.demo.service.impl.LocalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName AController
 * @Description TODO
 * @Author scott
 * @Date 2021/1/25 5:02 下午
 * @Version 1.0
 **/
@RestController
@RequestMapping("/tcc")
public class AController {
    @Resource
    private GlobalService globalService;
    @Resource
    private LocalService localService;

    /**
     * globalTransaction-->transactionA-->transactionB ...
     * @return
     */
    @GetMapping("/a")
    public StatusDTO requestA() {
        /**
         * 调用开启全局事务方法
         */
        globalService.startGlobalTransactional();
        return StatusDTO.buildDataSuccess("tcc调用成功!");
    }

    /**
     * transactionA-->globalTransaction-->transactionB-->transactionC ...
     * @return
     */
    @GetMapping("/b")
    public StatusDTO requestB() {
        /**
         * 调用开启本地事务方法
         */
        localService.startTransactional();
        return StatusDTO.buildDataSuccess("tcc调用成功!");
    }

    /**
     * transactionA-->globalTransaction-->transactionB-->transactionC ...
     * 错误写法
     * @return
     */
    @GetMapping("/c")
    @Deprecated
    public StatusDTO requestC() {
        /**
         * 调用开启本地事务方法
         */
        globalService.startTransactional();
        return StatusDTO.buildDataSuccess("tcc调用成功!");
    }

}
