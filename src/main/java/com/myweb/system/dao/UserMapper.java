package com.myweb.system.dao;

import com.myweb.system.model.User;
import com.myweb.system.model.UserArgs;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 */
public interface UserMapper {


    User findUserById(Long userId);

    User findUserByUsername(String username);

    @Transactional(rollbackFor = Exception.class)
    int addUser(User user);

    @Transactional(rollbackFor = Exception.class)
    int addEmployeeRole(User user);

    @Transactional(rollbackFor = Exception.class)
    int deleteUser(Long userId);

    @Transactional(rollbackFor = Exception.class)
    int editUser(User user);

    @Transactional(rollbackFor = Exception.class)
    int deleteEmployeeRole(Long userId);

    List<User> listUser(UserArgs args);

}
