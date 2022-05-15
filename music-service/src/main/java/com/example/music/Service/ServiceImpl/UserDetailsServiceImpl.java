package com.example.music.Service.ServiceImpl;

import com.example.music.Entity.Pojo.Entity.SecurityUser;
import com.example.music.Entity.Pojo.Entity.User;
import com.example.music.Entity.Util.LoginType;
import com.example.music.Mapper.BasicMapper;
import com.example.music.Mapper.PermissionMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private BasicMapper basicMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = basicMapper.selectUserByAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<String> list = permissionMapper.selectPermissionByLevel(user.getLevel());
        SecurityUser securityUser = new SecurityUser(user.getAccount(),user.getPassword(),list);
        securityUser.setUserId(user.getUserId());
        securityUser.setLoginType(LoginType.LOGIN_BY_ACCOUNT);
        securityUser.setLevel(user.getLevel());
        return securityUser;
    }

    public  User loadUserByEmail(String email){
        User user = basicMapper.selectUserByEmail(email);
        return user;
    }

}
