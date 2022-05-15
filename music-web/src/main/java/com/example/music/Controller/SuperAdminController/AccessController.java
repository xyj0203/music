package com.example.music.Controller.SuperAdminController;

import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "超级管理员操作")
@RequestMapping("/superAdmin")
public class AccessController {

    @Autowired
    private PermissionService permissionService;

//    public ResultObjectModel addPermission(String permissionName, String permissionDesc){
//        return permissionService.addPermission(permissionName,permissionDesc);
//    }

    @ApiOperation("/查询所有的角色")
    @GetMapping("/getAllRoles")
    public ResultObjectModel selectForAllRole(){
        return permissionService.selectForAllRole();
    }

}
