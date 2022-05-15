package com.example.music.Service.ServiceImpl;

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
}
