package com.example.music.Service.ServiceImpl;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.music.Entity.Pojo.Entity.Permission;
import com.example.music.Entity.Pojo.Entity.Role;
import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Mapper.PermissionMapper;
import com.example.music.Service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public ResultObjectModel selectForAllRole() {
        List<Role> list = permissionMapper.selectForAllRole();
        return ResultObjectModel.success(list);
    }

    @Override
    public ResultObjectModel addPermission(Permission permission) {
        int insert = permissionMapper.insert(permission);
        if (insert == 1){
            return ResultObjectModel.success("添加成功！");
        }
        return ResultObjectModel.fail("添加失败！");
    }

    @Override
    public ResultObjectModel deletePermission(Long permissionId) {
        int i = permissionMapper.deleteById(permissionId);
        if (i == 1){
            return ResultObjectModel.success("删除成功!");
        }
        return ResultObjectModel.success("删除失败！");
    }

    @Override
    public ResultObjectModel modifyPermission(Permission permission) {
        int i = permissionMapper.updateById(permission);
        if (i == 1){
            return ResultObjectModel.success("修改成功!");
        }
        return ResultObjectModel.success("修改失败！");
    }

    @Override
    public ResultObjectModel getAllPermission() {
        List<Permission> all = permissionMapper.findAll();
        return ResultObjectModel.success(all);
    }


}
