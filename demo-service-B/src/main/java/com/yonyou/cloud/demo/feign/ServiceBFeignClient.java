package com.yonyou.cloud.demo.feign;

import com.yonyou.cloud.common.constrants.ApplicationNameConstrant;
import com.yonyou.cloud.common.response.StatusDTO;
import com.yonyou.cloud.demo.req.RequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName ServiceBFeignClient
 * @Description TODO
 * @Author scott
 * @Date 2021/1/25 5:31 下午
 * @Version 1.0
 **/
@FeignClient(value = ApplicationNameConstrant.B_APPLICATION_NAME)
public interface ServiceBFeignClient {

    @PostMapping(value = "/tcc/b", produces = MediaType.APPLICATION_JSON_VALUE)
    StatusDTO requestBforUpdate(@RequestBody RequestDto orderReq);
}
