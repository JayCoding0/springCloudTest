package com.rds.consumer.controller;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rds.consumer.client.UserClient;
import com.rds.consumer.pojo.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "defaultFallBack")
public class ConsumerController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    //    @GetMapping("{id}")
    //    public User queryById(@PathVariable("id") Long id) {
    //        String url = "http://localhost:8081/user/" + id;
    //        User user = restTemplate.getForObject(url, User.class);
    //        return user;
    //    }
    @Autowired
    private UserClient userClient;

    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userClient.queryById(id);
    }

    //    @GetMapping("{id}")
    //    @HystrixCommand
    //    // @HystrixCommand(fallbackMethod = "queryByIdFallBack")
    //    public String queryById(@PathVariable("id") Long id) {
    //        // 根据服务id(spring.application.name)，获取服务实例列表
    //        // List<ServiceInstance> instances = discoveryClient.getInstances("user");
    //        // 取出一个服务实例
    //        // ServiceInstance instance = instances.get(0);
    //        // 从实例中获取host和port，组成url
    //        // String url = String.format("http://%s:%s/user/%s", instance.getHost(), instance.getPort(), id);
    //        if (id == 1) {
    //            throw new RuntimeException("太忙了");
    //        }
    //
    //        String url = "http://user/user/" + id;
    //        // 查询
    //        String user = restTemplate.getForObject(url, String.class);
    //        return user;
    //    }

    public String queryByIdFallBack(Long id) {
        log.error("查询用户信息失败，id：{}", id);
        return "对不起，网络太拥挤了！";
    }


    public String defaultFallBack() {

        return "对不起，网络太拥挤了！这是默认提示";
    }
}