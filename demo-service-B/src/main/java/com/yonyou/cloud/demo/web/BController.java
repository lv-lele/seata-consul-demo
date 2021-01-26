package com.yonyou.cloud.demo.web;

import com.yonyou.cloud.common.response.StatusDTO;
import com.yonyou.cloud.demo.req.RequestDto;
import com.yonyou.cloud.demo.service.DemoServiceB;
import io.seata.core.context.RootContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName BController
 * @Description TODO
 * @Author scott
 * @Date 2021/1/25 5:32 下午
 * @Version 1.0
 **/
@RestController
@RequestMapping("/tcc")
@Log4j2
public class BController {
    @Resource
    private DemoServiceB demoServiceB;

    @PostMapping(value = "/b", produces = MediaType.APPLICATION_JSON_VALUE)
    StatusDTO requestBforUpdate(@RequestBody RequestDto orderReq) {
        String xid = RootContext.getXID();
        demoServiceB.update(null, orderReq);
        log.info("demo-service-B 正在被调用!,xid = {}", xid);
        return StatusDTO.buildDataSuccess("调用成功");
    }
}
