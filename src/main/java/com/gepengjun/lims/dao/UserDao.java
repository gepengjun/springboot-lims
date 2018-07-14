package com.gepengjun.lims.dao;

import com.gepengjun.lims.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long>{

    User findByUsername(String username);
}
