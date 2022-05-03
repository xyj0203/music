package com.example.music.Service;

import com.example.music.Entity.Pojo.Entity.ThirdUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface ThirdService {
    /**
     * 载入第三方信息
     * @param thirdUser
     * @return
     */
    ThirdUser loadThirdUser(ThirdUser thirdUser);
}
