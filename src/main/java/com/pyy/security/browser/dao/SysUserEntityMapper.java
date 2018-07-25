package com.pyy.security.browser.dao;

import com.pyy.security.browser.model.SysUserEntity;

public interface SysUserEntityMapper {
    int deleteByPrimaryKey(String userId);

    int insert(SysUserEntity record);

    int insertSelective(SysUserEntity record);

    SysUserEntity selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUserEntity record);

    int updateByPrimaryKey(SysUserEntity record);

    SysUserEntity selectByUsername(String username);
}