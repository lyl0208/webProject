package com.myweb.system.dao;

import com.myweb.system.model.User;

/**
 */
public interface UserMapper {


    User findUserById(Long userId);

    User findUserByUsername(String username);

}
