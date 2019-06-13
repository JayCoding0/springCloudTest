package com.rds.consumer.client;

import com.rds.consumer.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author Jay
 * @date 2019/3/21 22:35
 * @description
 */
@Component
public class UserClientFallback implements UserClient {
    @Override
    public User queryById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("用户查询出现异常！");
        return user;
    }
}
