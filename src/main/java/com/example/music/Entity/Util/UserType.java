package com.example.music.Entity.Util;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum UserType {
    UNKNOWN(1,"用户","未登陆的用户"),
    USER(2,"用户","已经登陆的用户"),
    VIP_USER(3,"VIP用户","充值获得的VIP用户"),
    SINGERSUPER(4,"歌手","个人歌手"),
    ADMIN(5,"管理员","普通管理员，可以为其他管理员分配权限"),
    SUPER_ADMIN(6,"超级管理员","超级管理员，拥有所有权限"),
    THIRD_USER(7,"第三方登录","操作受限");
    public int Level;
    private String name;
    private String description;

    UserType(int level, String name, String description) {
        Level = level;
        this.name = name;
        this.description = description;
    }

    public int getLevel() {
        return Level;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
