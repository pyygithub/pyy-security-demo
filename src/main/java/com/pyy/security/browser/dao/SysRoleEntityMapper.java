package com.pyy.security.browser.dao;

import com.pyy.security.browser.model.SysRoleEntity;

import java.util.List;

public interface SysRoleEntityMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(SysRoleEntity record);

    int insertSelective(SysRoleEntity record);

    SysRoleEntity selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRoleEntity record);

    int updateByPrimaryKey(SysRoleEntity record);

    List<SysRoleEntity> selectRolesByUserId(String userId);
}