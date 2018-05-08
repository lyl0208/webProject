package com.myweb.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myweb.core.PageResult;
import com.myweb.core.utils.AesUtils;
import com.myweb.system.dao.UserMapper;
import com.myweb.system.model.Role;
import com.myweb.system.model.User;
import com.myweb.system.model.UserArgs;
import com.myweb.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(Long userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userMapper.findUserByUsername(username);
        for (Role role : user.getRoles()) {
            Collections.sort(role.getPermissions());
        }
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(User user) {
        try {
            user.setPassword(AesUtils.MD5("123456"));
            user.setCreateTime(new Date());
            return userMapper.addUser(user) > 0 && userMapper.addEmployeeRole(user) > 0;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUser(Long userId) {
        userMapper.deleteEmployeeRole(userId);
        return userMapper.deleteUser(userId) > 0;
    }

    @Override
    public boolean editUser(User user) {
        return userMapper.editUser(user) > 0;
    }

    @Override
    public PageResult listUser(UserArgs userArgs) {
        Page<Object> startPage = PageHelper.startPage(userArgs.getPage(), userArgs.getLimit());
        List<User> users = userMapper.listUser(userArgs);
        PageResult<User> result = new PageResult<>();
        result.setData(users);
        result.setCount(startPage.getTotal());
        return result;
    }

    @Override
    public boolean resetPassword(Long userId) {
        User user = userMapper.findUserById(userId);
        user.setPassword("123456");
        return userMapper.resetPassword(user) > 0;
    }

    @Override
    public boolean editPassword(Long userId, String newPsw) {
        return userMapper.editPassword(userId,newPsw) > 0;
    }


}
