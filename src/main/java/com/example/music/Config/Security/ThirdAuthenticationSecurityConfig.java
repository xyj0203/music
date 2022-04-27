package com.example.music.Config.Security;

import com.example.music.Config.Security.Filter.ThirdAuthenticationFilter;
import com.example.music.Config.Security.Provider.EmailAuthenticationProvider;
import com.example.music.Config.Security.Provider.GithubAuthenticationProvider;
import com.example.music.Utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
public class ThirdAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private EmailAuthenticationProvider emailAuthenticationProvider;
    @Autowired
    private GithubAuthenticationProvider githubAuthenticationProvider;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        //添加第三方过滤器至过滤器链
        ThirdAuthenticationFilter thirdAuthenticationFilter = new ThirdAuthenticationFilter(redisUtils);

        http.addFilterBefore(thirdAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

        thirdAuthenticationFilter.setAuthenticationManager(authenticationManager);
        //添加第三方认证提供者
        http.authenticationProvider(emailAuthenticationProvider);
        http.authenticationProvider(githubAuthenticationProvider);
        super.configure(http);
    }
}
