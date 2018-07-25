package com.pyy.security.browser.dao;

import com.pyy.security.browser.model.SysPermissionEntity;

public interface SysPermissionEntityMapper {
    int deleteByPrimaryKey(String permisId);

    int insert(SysPermissionEntity record);

    int insertSelective(SysPermissionEntity record);

    SysPermissionEntity selectByPrimaryKey(String permisId);

    int updateByPrimaryKeySelective(SysPermissionEntity record);

    int updateByPrimaryKey(SysPermissionEntity record);
}