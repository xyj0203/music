package com.example.music.Entity.Pojo.Entity;

import com.example.music.Entity.Util.LoginType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class SecurityUser implements UserDetails {

    //用户id
    private Long userId;
    //登录方式
    private LoginType loginType;
    //用户的角色
    private Integer level;
    @ApiModelProperty(value = "用户权限列表")
    private List<String> permissionValueList;
    private String account;
    private String password;

    public SecurityUser( String account, String password,List<String> permissionValueList ) {
        this.permissionValueList = permissionValueList;
        this.account = account;
        this.password = password;
    }

    public SecurityUser(Long userId,Integer level,List<String> permissionValueList) {
        this.userId = userId;
        this.level = level;
        this.permissionValueList = permissionValueList;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> athorities = new ArrayList<>();
        for (String permissionValue :
                permissionValueList) {
            if (StringUtils.hasLength(permissionValue)) {
                athorities.add(new SimpleGrantedAuthority("ROLE_" + permissionValue));
            }
        }
        return athorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
