package com.example.music.Entity.Pojo.Entity;

import com.example.music.Entity.Util.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long userId;
    private String account;
    @Email(message = "必须为邮箱格式")
    private String email;
    private String password;
    private Integer sex;
    private Date birthday;
    private String username;
    private String headImg = "https://profile-avatar.csdnimg.cn/f6f9d8c044624e33a9b58aba68376925_weixin_49919104.jpg!2";
    private UserType userType = UserType.USER;
    private Integer state = 0;
    private String area;
    private Integer online = 1;
    private Boolean isThirdLogin = false;
    private Integer level;
}
