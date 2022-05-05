package com.example.music.Service;

import com.example.music.Entity.Pojo.ResultObjectModel;

public interface PermissionService {
    /**
     * 查询所有的角色
     * @return
     */
    ResultObjectModel selectForAllRole();
}
