package cn.rds.user.service;

import cn.rds.user.mapper.UserMapper;
import cn.rds.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jay
 * @date 2019/3/9 17:52
 * @description
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
