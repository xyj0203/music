package com.example.music.Mapper;

import com.example.music.Entity.Pojo.Entity.ThirdUser;

public interface ThirdMapper {
    /**
     * 存在就更新
     * @return
     */
    int updateifexist(ThirdUser thirdUser);

    /**
     * 获取第三方登录的实例
     * @param thirdId
     * @return
     */
    ThirdUser selectByThirdId(Long thirdId);
}
