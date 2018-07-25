package com.pyy.security.browser.dto;

import com.pyy.security.browser.model.SysUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author: pyygithub
 * @date: 2018-07-22 15:53
 * @version: v1.0
 */
public class MyUserDetails implements UserDetails {
    private SysUserEntity userEntity;
    private String roles;

    public MyUserDetails(SysUserEntity userEntity, String roles) {
        this.userEntity = userEntity;
        this.roles = roles;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    // 用户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return userEntity.getExpired() ? false : true;
    }

    // 用户是否未锁定，冻结
    @Override
    public boolean isAccountNonLocked() {
       return userEntity.getLocked() ? false : true;
    }

    // 密码是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 用户是否未可用，被删除
    @Override
    public boolean isEnabled() {
       return userEntity.getDeleted() ? false : true;
    }
}
