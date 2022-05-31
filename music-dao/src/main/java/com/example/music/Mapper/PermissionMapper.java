package com.example.music.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.music.Entity.Pojo.Entity.Permission;
import com.example.music.Entity.Pojo.Entity.Role;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 查询所有权限
     * @return
     */
    List<Permission> findAll();

    List<Permission> findAllByUserId(Long roleId);

    /**
     * 通过等级查询权限
     * @param level
     * @return
     */
    List<String> selectPermissionByLevel(Integer level);

    /**
     * 通过等级查询所对应的操作权限列表
     * @param parseInt
     * @return
     */
    List<String> getNameByLevel(int parseInt);

    /**
     * 查询所有的角色
     * @return
     */
    List<Role> selectForAllRole();
}
