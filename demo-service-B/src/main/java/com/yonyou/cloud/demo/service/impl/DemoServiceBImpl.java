package com.yonyou.cloud.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yonyou.cloud.demo.req.RequestDto;
import com.yonyou.cloud.demo.service.DemoServiceB;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @ClassName DemoServiceAImpl
 * @Description TODO
 * @Author scott
 * @Date 2021/1/25 5:06 下午
 * @Version 1.0
 **/
@Service
@Log4j2
public class DemoServiceBImpl implements DemoServiceB {

    @Override
    public boolean update(BusinessActionContext actionContext, RequestDto requestDto) {
        String xid = actionContext.getXid();
        long branchId = actionContext.getBranchId();
        log.info("demo-service-B,try阶段执行,xid ={}, branchId = {}", xid, branchId);
        log.info("demo-service-B 执行update,做资源预留,updateDto = {}", requestDto);
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        int error = 1 / 0;
        String xid = actionContext.getXid();
        long branchId = actionContext.getBranchId();
        log.info("demo-service-B,commit阶段执行,全局事务xid ={},分支类型 branchId = {}", xid, branchId);
        String updateDto = ((JSONObject) actionContext.getActionContext("requestDtoB")).toString();
        log.info("demo-service-B 执行update,将预留资源真正更新,释放预留资源，updateDto = {}", updateDto);
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        long branchId = actionContext.getBranchId();
        log.info("demo-service-B,rollback阶段执行,全局事务xid ={},分支类型 branchId = {}", xid, branchId);
        String updateDto = ((JSONObject) actionContext.getActionContext("requestDtoB")).toString();
        log.info("demo-service-B 执行update,释放预留资源，updateDto = {}", updateDto);
        return true;
    }
}
