package com.example.music.Entity.Pojo.Entity;

import com.example.music.Entity.Util.UserType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "x_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String account;
    @Email(message = "必须为邮箱格式")
    private String email;
    private String password;
    private Integer sex;
    private Date birthday;
    private String username = "未知";
    private String headImg = "https://profile-avatar.csdnimg.cn/f6f9d8c044624e33a9b58aba68376925_weixin_49919104.jpg!2";
    private UserType userType = UserType.USER;
    private Integer state = 0;
    private String area = "未知";
    private Boolean isThirdLogin = false;
    private Integer level;
    private String signature ="未知";
}
