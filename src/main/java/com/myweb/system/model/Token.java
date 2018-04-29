package com.myweb.system.model;


import java.util.Date;

/**
 * Token信息
 */
public class Token {
    private Long userId;
    private String username;
    private Long loginTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkToken() {
        return userId != null && username != null && loginTime != null;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }
}
