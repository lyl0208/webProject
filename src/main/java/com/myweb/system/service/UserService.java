package com.myweb.system.service;

import com.myweb.core.PageResult;
import com.myweb.system.model.User;
import com.myweb.system.model.UserArgs;

/**
 */
public interface UserService {


    User findUserById(Long userId);

    User findUserByUsername(String username);

    boolean addUser(User user);

    boolean deleteUser(Long userId);

    boolean editUser(User user);

    PageResult listUser(UserArgs userArgs);

    boolean resetPassword(Long userId);

    boolean editPassword( Long userId, String newPsw);

}
