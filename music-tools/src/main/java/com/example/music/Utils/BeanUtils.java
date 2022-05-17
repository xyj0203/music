package com.example.music.Utils;

import com.example.music.Entity.Pojo.Entity.User;
import com.example.music.Entity.Pojo.Vo.UserVo;
import com.example.music.Entity.Util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BeanUtils {
    @Resource
    private CompontUtil compontUtil;
    @Resource
    private RedisUtils redisUtils;

    public UserVo userToUserVo(User user){
        UserVo userVo = new UserVo();
        org.springframework.beans.BeanUtils.copyProperties(user,userVo);
        Integer sex = user.getSex();
        if (sex == null){
            userVo.setSex("未知");
        }else if (sex == 0){
            userVo.setSex("男");
        }else if (sex == 1){
            userVo.setSex("女");
        }
        Date birthday = user.getBirthday();
        if (birthday == null){
            userVo.setBirthday("未知");
        }else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(birthday);
            userVo.setBirthday(format);
            userVo.setAge(compontUtil.yearDateDiff(birthday.getTime(),System.currentTimeMillis()));
        }
        boolean bit = redisUtils.getBit(RedisKeyUtils.ONLINE_STATE, user.getUserId());
        if (bit){
            userVo.setOnline("在线");
        }else {
            userVo.setOnline("离线");
        }
        UserType userType = UserType.getUserType(user.getLevel());
        userVo.setLevel(userType.getName());
        return userVo;
    }
}
