package com.example.music.Config.Security.Provider;

import cn.hutool.core.lang.Validator;
import com.example.music.Config.Security.Token.EmailAuthenticationToken;
import com.example.music.Entity.CustomzieException;
import com.example.music.Utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.ArrayList;

@Component
public class EmailAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EmailAuthenticationToken token = (EmailAuthenticationToken) authentication;
        String email = token.getPrincipal();
        String code = token.getCredentials();
        EmailAuthenticationToken auth = new EmailAuthenticationToken(email, code,new ArrayList<GrantedAuthority>());
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EmailAuthenticationToken.class.isAssignableFrom(authentication);
    }


}
