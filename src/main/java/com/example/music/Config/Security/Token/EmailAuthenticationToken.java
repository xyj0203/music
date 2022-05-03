package com.example.music.Config.Security.Token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class EmailAuthenticationToken extends AbstractAuthenticationToken {
    //邮箱
    private Object principle;
    //验证码
    private Object credential;
    //后台产生验证码
    private String code;

    public EmailAuthenticationToken(Object email, Object credential) {
        super(null);
        this.principle = email;
        this.credential = credential;
        setAuthenticated(false);
    }

    public EmailAuthenticationToken(Object email, Object credential, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principle = email;
        this.credential = credential;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return this.credential;
    }

    @Override
    public Object getPrincipal() {
        return this.principle;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credential = null;
    }
}
