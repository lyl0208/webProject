package com.myweb.system.model;

import java.util.Date;

/**
 * 权限
 */
public class Permission {


    private Long permissionId;

    private Long pid;

    private String permissionName;

    private String permissionValue;

    private Date createTime;

    public Long getPermissionId() {
        return permissionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }
}
