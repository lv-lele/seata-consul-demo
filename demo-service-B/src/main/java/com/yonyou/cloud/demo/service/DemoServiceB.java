package com.yonyou.cloud.demo.service;

import com.yonyou.cloud.demo.req.RequestDto;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName DemoServiceA
 * @Description TODO
 * @Author scott
 * @Date 2021/1/25 5:04 下午
 * @Version 1.0
 **/
@LocalTCC
public interface DemoServiceB {
    /**
     * update
     *
     * @param actionContext
     * @param requestDto
     * @return
     * @throws Exception
     */
    @TwoPhaseBusinessAction(name = "DemoServiceB")
    boolean update(BusinessActionContext actionContext, @RequestBody @BusinessActionContextParameter(paramName = "requestDtoB") RequestDto requestDto);

    /**
     * commit
     *
     * @param actionContext
     * @return
     */
    boolean commit(BusinessActionContext actionContext);

    /**
     * rollback
     *
     * @param actionContext
     * @return
     */
    boolean rollback(BusinessActionContext actionContext);
}
