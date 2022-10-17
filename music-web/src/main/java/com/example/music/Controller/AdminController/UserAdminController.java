package com.example.music.Controller.AdminController;

import cn.hutool.Hutool;
import cn.hutool.core.lang.Validator;
import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户管理")
@RequestMapping("/adminUser")
public class UserAdminController {

    @Autowired
    private UserService userService;

    public ResultObjectModel listUser(){
        return null;
    }

    @ApiOperation("管理修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id"),
            @ApiImplicitParam(name = "newPassword", value = "新密码")
    }
    )
    @PutMapping("modifyUserPassword")
    public ResultObjectModel updateUserPassword(Long userId,String newPassword){
        if (Validator.isEmpty(userId) || Validator.isEmpty(newPassword)){
            return ResultObjectModel.fail("参数有误！");
        }
        return userService.updatePassword(userId,newPassword);
    }
}
