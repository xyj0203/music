package com.example.music.Config.Security.Interceptor;

import com.example.music.Entity.Pojo.Entity.Permission;
import com.example.music.Mapper.PermissionMapper;
import com.example.music.Utils.RedisKeyUtils;
import com.example.music.Utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionMapper permissionMapper;
    private Map<String, Collection<ConfigAttribute>> map =null;
    @Autowired
    private RedisUtils redisUtils;
    public void loadResourceDefine(){
        Object o = redisUtils.get(RedisKeyUtils.PERMISSION_LIST);
        List<Permission> permissions = null;
        if(o == null){
            permissions = permissionMapper.findAll();
            redisUtils.set(RedisKeyUtils.PERMISSION_LIST,permissions);
        }else{
            permissions = (List<Permission>) o;
        }
        map = new ConcurrentHashMap<>();
        Collection<ConfigAttribute> configAttributes;
        ConfigAttribute configAttribute;
        for (Permission permission : permissions) {
            configAttributes = new ArrayList<>();
            configAttribute = new SecurityConfig(permission.getName());
            configAttributes.add(configAttribute);
            map.put(permission.getPath(), configAttributes);
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        loadResourceDefine();
        //获取用户请求的地址
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String requestUrl;
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ){
            requestUrl = iter.next();
            matcher = new AntPathRequestMatcher(requestUrl);
            if(matcher.matches(request)){
                return map.get(requestUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
