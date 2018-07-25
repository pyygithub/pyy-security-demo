package com.pyy.security.browser.service;

import com.pyy.common.exception.PyyException;
import com.pyy.security.browser.dao.SysRoleEntityMapper;
import com.pyy.security.browser.dao.SysUserEntityMapper;
import com.pyy.security.browser.dto.MyUserDetails;
import com.pyy.security.browser.model.SysRoleEntity;
import com.pyy.security.browser.model.SysUserEntity;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义UserDetailsService获取用户身份信息
 * @author: pyygithub
 * @date: 2018-07-22 15:40
 * @version: v1.0
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserEntityMapper sysUserEntityMapper;

    @Autowired
    private SysRoleEntityMapper sysRoleEntityMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("【用户登录】, 根据用户名查询用户信息,username={}", username);

        // 根据用户名去数据查询用户信息
        SysUserEntity userEntity = sysUserEntityMapper.selectByUsername(username);
        if(userEntity == null) {
            log.error("【用户登录】当前登录用户不存在,username={}",username);
            throw new UsernameNotFoundException(username);
        }
        // 根据用户id查询用户角色列表信息
        List<SysRoleEntity> roleEntityList = sysRoleEntityMapper.selectRolesByUserId(userEntity.getUserId());
        if(CollectionUtils.isEmpty(roleEntityList)) {
            throw new PyyException("用户角色信息查询失败");
        }
        String roles = roleEntityList.stream().map(SysRoleEntity::getName).collect(Collectors.joining(","));

        return new MyUserDetails(userEntity, roles);
    }
}
