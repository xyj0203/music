package com.example.music.Entity.Util;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum UserType {
    UNKNOWN(0,"用户","未登陆的用户"),
    USER(1,"用户","已经登陆的用户"),
    VIP_USER(2,"VIP用户","充值获得的VIP用户"),
    SINGERSUPER(3,"歌手","个人歌手"),
    ADMIN(4,"管理员","普通管理员，可以为其他管理员分配权限"),
    SUPER_ADMIN(5,"超级管理员","超级管理员，拥有所有权限");
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