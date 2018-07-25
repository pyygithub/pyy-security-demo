package com.pyy.security.browser.dao;

import com.pyy.security.browser.model.SysRolePermissionEntityKey;

public interface SysRolePermissionEntityMapper {
    int deleteByPrimaryKey(SysRolePermissionEntityKey key);

    int insert(SysRolePermissionEntityKey record);

    int insertSelective(SysRolePermissionEntityKey record);
}