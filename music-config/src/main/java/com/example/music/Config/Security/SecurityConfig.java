package com.example.music.Config.Security;

import com.example.music.Config.Security.Interceptor.AccessSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ThirdAuthenticationSecurityConfig thirdAuthenticationSecurityConfig;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AccessSecurityInterceptor accessSecurityInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v2/**",
                        "/api/**",
                        "/doc.html/**",
                        "/Basic/**",
                        "/favicon.ico",
                        "/message/**")
                .permitAll();

        http.authorizeRequests()
                .and()
                .formLogin()
                .loginProcessingUrl("/login/form")
                .failureUrl("/login/form?error")
                .permitAll();
        http.exceptionHandling().authenticationEntryPoint(new UnauthEntryPoint())
                .accessDeniedHandler(new CustomizedAccessDeniedHandler());
        http.apply(thirdAuthenticationSecurityConfig);
        http.csrf().disable().
                authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(accessSecurityInterceptor, FilterSecurityInterceptor.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
