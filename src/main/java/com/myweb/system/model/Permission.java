package com.myweb.system.model;

import java.util.Date;
import java.util.List;

/**
 * 权限
 */
public class Permission {


    private Long permissionId;

    private Long pid;

    private String permissionName;

    private String permissionValue;

    private String permissionIcon;

    private List<Permission> subPermission;

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

    public List<Permission> getSubPermission() {
        return subPermission;
    }

    public void setSubPermission(List<Permission> subPermission) {
        this.subPermission = subPermission;
    }

    public String getPermissionIcon() {
        return permissionIcon;
    }

    public void setPermissionIcon(String permissionIcon) {
        this.permissionIcon = permissionIcon;
    }
}
