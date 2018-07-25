package com.pyy.security.browser.model;

public class SysRolePermissionEntityKey {
    private String roleId;

    private String permisId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getPermisId() {
        return permisId;
    }

    public void setPermisId(String permisId) {
        this.permisId = permisId == null ? null : permisId.trim();
    }
}