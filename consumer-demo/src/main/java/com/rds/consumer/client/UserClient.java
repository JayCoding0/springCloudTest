package com.rds.consumer.client;

import com.rds.consumer.config.FeignConfig;
import com.rds.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Jay
 * @date 2019/3/21 22:29
 * @description
 */
@FeignClient(value = "user", fallback = UserClientFallback.class, configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/user/{id}")
    User queryById(@PathVariable("id") Long id);
}