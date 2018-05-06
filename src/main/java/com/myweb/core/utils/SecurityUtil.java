package com.myweb.core.utils;

import com.myweb.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtil {

    @Autowired
    private UserService userService;

    /**
     * 获取当前用户Id
     */
    public static Long getCurrentUserId() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return Long.valueOf(String.valueOf(request.getAttribute("userId")));
    }


}
