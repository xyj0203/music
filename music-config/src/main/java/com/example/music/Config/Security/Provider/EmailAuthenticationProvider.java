package com.example.music.Config.Security.Provider;

import com.example.music.Config.Security.Token.EmailAuthenticationToken;
import com.example.music.Entity.Pojo.Entity.SecurityUser;
import com.example.music.Entity.Pojo.Entity.User;
import com.example.music.Entity.Util.LoginType;
import com.example.music.Mapper.PermissionMapper;
import com.example.music.Service.ServiceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class EmailAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EmailAuthenticationToken token = (EmailAuthenticationToken) authentication;
        String email = (String) token.getPrincipal();
        User user = userDetailsService.loadUserByEmail(email);
        List<String> list = permissionMapper.selectPermissionByLevel(user.getLevel());
        SecurityUser securityUser = new SecurityUser(user.getAccount(),user.getPassword(),list);
        securityUser.setUserId(user.getUserId());
        securityUser.setLoginType(LoginType.LOGIN_BY_EMAIL);
        securityUser.setLevel(user.getLevel());
        EmailAuthenticationToken auth = new EmailAuthenticationToken(securityUser, null,securityUser.getAuthorities());
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EmailAuthenticationToken.class.isAssignableFrom(authentication);
    }


}
