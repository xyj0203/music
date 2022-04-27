package com.example.music.Service.ServiceImpl;

import com.example.music.Entity.Pojo.Entity.User;
import com.example.music.Mapper.BasicMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private BasicMapper basicMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = basicMapper.selectUserByAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> "ROLE_USER");
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getAccount(),user.getPassword(),authorities);
        return userDetails;
    }
}
