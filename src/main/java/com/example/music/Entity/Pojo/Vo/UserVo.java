package com.example.music.Entity.Pojo.Vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserVo {
    private Long userId;
    private String account;
    private String email;
    private Character sex;
    private String birthday;
    private String username;
    private String headImg;
    private String online;
    private String area;
}
