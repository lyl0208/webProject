package com.myweb.system.model;

import com.myweb.core.BaseArgs;

/**
 * 登陆请求参数
 */
public class LoginArgs extends BaseArgs {

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
