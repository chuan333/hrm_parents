package com.lbc.hrm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: hrm_parents
 * @Date: 2019/9/7 23:31
 * @Author: Mr.Deng
 * @Description:
 */
@FeignClient(value = "hrm-redis",configuration = FeignClientsConfiguration.class
    ,fallbackFactory = RedisClientFallbackFactory.class)
public interface RedisClient {
    @PostMapping
    void set(@RequestParam("key") String key,@RequestParam("value") String value);
    @GetMapping
    String get(@RequestParam("key") String key);

}
