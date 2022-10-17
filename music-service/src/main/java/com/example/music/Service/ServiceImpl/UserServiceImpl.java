package com.example.music.Service.ServiceImpl;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Repository.UserRepository;
import com.example.music.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResultObjectModel updatePassword(Long userId, String newPassword) {
        int i = userRepository.modifyPassword(userId, passwordEncoder.encode(newPassword));
        return i == 1 ? ResultObjectModel.success("修改成功！") : ResultObjectModel.fail("修改失败！");
    }
}
