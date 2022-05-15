package com.example.music.Service.ServiceImpl;

import com.example.music.Entity.Pojo.Entity.User;
import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Mapper.BasicMapper;
import com.example.music.Service.BasicService;
import com.example.music.Utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BasicServiceImpl implements BasicService {

    @Autowired
    private RedisUtils redisUtils;
    @Resource
    private BasicMapper basicMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public ResultObjectModel register(String email, String code, String password, String account) {
        String scode = (String) redisUtils.get(email);
        if (scode == null) {
            return ResultObjectModel.fail( "验证码已过期");
        }
        if(basicMapper.selectUserByEmail(email) != null){
            return ResultObjectModel.fail("邮箱已被注册");
        }
        if(basicMapper.selectUserByAccount(account) != null){
            return ResultObjectModel.fail("账号已被注册");
        }
        User user = new User();
        user.setLevel(2);
        user.setAccount(account);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        if (basicMapper.register(user) == 1) {
            return ResultObjectModel.success("注册成功");
        }
        return ResultObjectModel.fail("注册失败");
    }
}
