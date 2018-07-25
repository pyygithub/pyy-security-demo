package com.pyy.security.browser.model;

public class SysPermissionEntity {
    private String permisId;

    private String name;

    private String description;

    private String url;

    private String pid;

    public String getPermisId() {
        return permisId;
    }

    public void setPermisId(String permisId) {
        this.permisId = permisId == null ? null : permisId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }
}