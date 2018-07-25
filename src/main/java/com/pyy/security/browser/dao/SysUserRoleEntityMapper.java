package com.pyy.security.browser.dao;

import com.pyy.security.browser.model.SysUserRoleEntityKey;

public interface SysUserRoleEntityMapper {
    int deleteByPrimaryKey(SysUserRoleEntityKey key);

    int insert(SysUserRoleEntityKey record);

    int insertSelective(SysUserRoleEntityKey record);
}