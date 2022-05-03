package com.example.music.Entity.Pojo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Permission {
    //id
    private Integer permission_Id;
    //权限形容
    private String description;
    //权限路径
    private String path;
    //权限名称
    private String name;
}
