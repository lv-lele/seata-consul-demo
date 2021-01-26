package com.yonyou.cloud.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yonyou.cloud.demo.req.RequestDto;
import com.yonyou.cloud.demo.service.DemoServiceA;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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
public class DemoServiceAImpl implements DemoServiceA {


    @Override
    public boolean insert(BusinessActionContext actionContext, RequestDto requestDto) {
        String xid = actionContext.getXid();
        long branchId = actionContext.getBranchId();
        log.info("demo-service-A,try阶段执行,全局事务xid ={}, branchId = {}", xid, branchId);
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        long branchId = actionContext.getBranchId();
        log.info("demo-service-A,commit阶段执行,全局事务xid ={}, branchId = {}", xid, branchId);
        String insertDto = ((JSONObject) actionContext.getActionContext("requestDtoA")).toString();
        log.info("demo-service-A 执行insert,提交事务,insertDto = {}", insertDto);
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        long branchId = actionContext.getBranchId();
        log.info("demo-service-A,rollback阶段执行,全局事务xid ={}, branchId = {}", xid, branchId);
        String insertDto = ((JSONObject) actionContext.getActionContext("requestDtoA")).toString();
        log.info("demo-service-A 执行delete,进行回滚，insertDto = {}", insertDto);
        return true;
    }
}
