package com.example.music.Service.ServiceImpl;

import com.example.music.Entity.Pojo.Entity.ThirdUser;
import com.example.music.Mapper.ThirdMapper;
import com.example.music.Service.ThirdService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ThirdServiceImpl implements ThirdService {

    @Resource
    private ThirdMapper thirdMapper;

    @Override
    public ThirdUser loadThirdUser(ThirdUser thirdUser) {
        thirdMapper.updateifexist(thirdUser);
        ThirdUser user = thirdMapper.selectByThirdId(thirdUser.getThirdAccountId());
        return user;
    }
}
