package com.myweb.system.controller;

import com.myweb.core.ResultMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录相关controller
 */
@RestController
public class LoginController {


    /**
     * 登录
     */
    @PostMapping("/login")
    public ResultMap login(String username, String password, String vercode, String verkey , HttpServletRequest request) {
        return ResultMap.ok();
    }


}
