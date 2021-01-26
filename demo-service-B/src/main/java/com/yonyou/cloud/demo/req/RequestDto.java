package com.yonyou.cloud.demo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName RequestDto
 * @Description TODO
 * @Author scott
 * @Date 2021/1/25 5:08 下午
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    private Long id;

    private String name;
}
