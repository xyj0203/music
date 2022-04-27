package com.example.music.Mapper;

import com.example.music.Entity.Pojo.Entity.User;
import io.swagger.models.auth.In;
import org.mapstruct.Mapper;

@Mapper
public interface BasicMapper {
    /**
     * 注册用户
     * @param user
     * @return
     */
    Integer register(User user);

    /**
     * 通过邮箱查询用户
     * @param email
     * @return
     */
    User selectUserByEmail(String email);

    /**
     * 通过用户名查询用户
     * @param account
     * @return
     */
    User selectUserByAccount(String account);
}
