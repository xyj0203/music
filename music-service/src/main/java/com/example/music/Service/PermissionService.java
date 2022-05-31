package com.example.music.Service;

import com.example.music.Entity.Pojo.Entity.Permission;
import com.example.music.Entity.Pojo.ResultObjectModel;

public interface PermissionService {
    /**
     * 查询所有的角色
     * @return
     */
    ResultObjectModel selectForAllRole();

    /**
     * 添加权限
     * @param permission
     * @return
     */
    ResultObjectModel addPermission(Permission permission);

    /**
     * 删除权限
     * @param permissionId
     * @return
     */
    ResultObjectModel deletePermission(Long permissionId);

    /**
     * 修改权限
     * @param permission
     * @return
     */
    ResultObjectModel modifyPermission(Permission permission);

    /**
     * 获取所有权限
     * @return
     */
    ResultObjectModel getAllPermission();
}
