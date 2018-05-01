package com.myweb.system.controller;

import com.myweb.core.BaseController;
import com.myweb.core.PageResult;
import com.myweb.core.ResultMap;
import com.myweb.system.model.User;
import com.myweb.system.model.UserArgs;
import com.myweb.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    @PostMapping("/addUser")
    public ResultMap addUser(User user) {
        if (userService.addUser(user)) {
            return ResultMap.ok("添加成功");
        } else {
            return ResultMap.error("添加失败");
        }
    }

    @PostMapping("/listUser")
    public PageResult listUser(UserArgs args) {
        return userService.listUser(args);
    }

    @PostMapping("/deleteUser")
    public ResultMap deleteUser(Long userId){
        if (userService.deleteUser(userId)) {
            return ResultMap.ok("删除成功");
        } else {
            return ResultMap.error("删除失败");
        }
    }

    @PostMapping("/editUser")
    public ResultMap editUser(User user) {
        if (userService.editUser(user)) {
            return ResultMap.ok("修改成功");
        } else {
            return ResultMap.error("修改失败");
        }
    }

    @PostMapping("/resetPassword")
    public ResultMap resetPassword(Long userId) {
        if (userService.resetPassword(userId)) {
            return ResultMap.ok("修改成功");
        } else {
            return ResultMap.error("修改失败");
        }
    }

    @PostMapping("/editPassword")
    public ResultMap editPassword(String newPsw, HttpServletRequest request) {
        Long userId = getUserId(request);
        if (userService.editPassword(userId,newPsw)) {
            return ResultMap.ok("修改成功,请重新登陆");
        } else {
            return ResultMap.error("修改失败");
        }
    }

}
