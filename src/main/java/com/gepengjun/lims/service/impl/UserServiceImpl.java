package com.gepengjun.lims.service.impl;

import com.gepengjun.lims.dao.UserDao;
import com.gepengjun.lims.entity.User;
import com.gepengjun.lims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
