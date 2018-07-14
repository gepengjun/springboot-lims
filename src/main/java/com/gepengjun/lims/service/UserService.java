package com.gepengjun.lims.service;

import com.gepengjun.lims.entity.User;

public interface UserService {

    User findByUsername(String username);
}
