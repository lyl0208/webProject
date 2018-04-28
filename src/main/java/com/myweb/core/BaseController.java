package com.myweb.core;

import javax.servlet.http.HttpServletRequest;

/**
 */
public class BaseController {


    /**
     * 获取登录的User
     */
    public String getUserId(HttpServletRequest request) {return (String) request.getAttribute("userId");}


    /**
     * 获取当前请求的token
     */
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
        }
        return  token;
    }

}
