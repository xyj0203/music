package com.example.music.Mapper;

import com.example.music.Entity.Pojo.Entity.Img;
import com.example.music.Entity.Pojo.Entity.User;
import io.swagger.models.auth.In;
import org.mapstruct.Mapper;

import java.util.List;

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

    /**
     * 或群瀑布流图片
     * @param name
     * @return
     */
    List<Img> getImg(String name);
}
