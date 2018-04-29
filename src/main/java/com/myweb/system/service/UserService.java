package com.myweb.system.service;

import com.myweb.system.model.User;

/**
 */
public interface UserService {


    User findUserById(Long userId);

    User findUserByUsername(String username);

}
