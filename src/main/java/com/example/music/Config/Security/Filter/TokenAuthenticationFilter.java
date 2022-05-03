package com.example.music.Config.Security.Filter;

import com.example.music.Config.Security.TokenManager;
import com.example.music.Entity.Pojo.Entity.SecurityUser;
import com.example.music.Mapper.PermissionMapper;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private TokenManager tokenManager;
    private PermissionMapper permissionMapper;
    public TokenAuthenticationFilter(TokenManager tokenManager,PermissionMapper permissionMapper) {
        this.tokenManager = tokenManager;
        this.permissionMapper = permissionMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        AbstractAuthenticationToken authentication = getAuthentication(request);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        filterChain.doFilter(request, response);
    }

    private AbstractAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader("token");
        if (token != null && tokenManager.verfyToken(token)) {
            Map<String, String> map = tokenManager.getUserInfoFromToken(token);
            if (map == null) {
                return null;
            }
            String id = map.get("userId");
            String level = map.get("level");
            SecurityUser securityUser = new SecurityUser(Long.parseLong(id),Integer.parseInt(level),new ArrayList<>());
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            permissionMapper.getNameByLevel(Integer.parseInt(level)).forEach(
                    name -> authorities.add(new SimpleGrantedAuthority(name))
            );
            return new UsernamePasswordAuthenticationToken(securityUser, token, authorities);
        }
        return null;
    }
}
