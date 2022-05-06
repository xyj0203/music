package com.example.music.Controller.UserController;

import com.example.music.Entity.Pojo.Entity.SecurityUser;
import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Service.LinkedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;

@Api(tags = "联系人接口")
@RestController
@RequestMapping("/linkedman")
public class LinkedManController {

    @Autowired
    private LinkedService linkedService;

    @ApiOperation(value = "获取联系人列表", notes = "获取联系人列表")
    @GetMapping("/list")
    public ResultObjectModel getLinkedManList(@ApiIgnore @AuthenticationPrincipal SecurityUser user) {
        return linkedService.getLinkedManList(user.getUserId());
    }

    @ApiOperation(value = "删除联系人", notes = "删除联系人")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query")
    @GetMapping("/delete")
    public ResultObjectModel deleteLinkedMan(@NotNull Long userId) {
        return null;
    }

    @ApiOperation(value = "修改联系人", notes = "修改联系人")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query")
    @GetMapping("/update")
    public ResultObjectModel updateLinkedMan(@NotNull Long userId) {
        return null;
    }

    @ApiOperation(value = "申请添加联系人", notes = "申请添加联系人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sendContent", value = "申请内容", required = true, dataType = "String", paramType = "query"),
    })
    @GetMapping("/add")
    public ResultObjectModel addLinkedMan(@NotNull Long userId,
                                          @ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,
                                          @NotNull String sendContent) {
        if (securityUser.getUserId().equals(userId)) {
            return ResultObjectModel.success("无法添加自己为好友！");
        }
        return linkedService.addLinkedMan(securityUser.getUserId(),userId,sendContent);
    }

    @ApiOperation(value = "联系人申请状态修改", notes = "0未同意 1同意 2拒绝")
    @PutMapping("/updateStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "申请用户id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态 1同意 2拒绝", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "applyId", value = "申请id", required = true, dataType = "String", paramType = "query")
    })
    public ResultObjectModel acceptLinkedMan(@NotNull Long userId,
                                             @NotNull Long applyId,
                                             @ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,
                                             @NotNull @Range(max = 2,min = 1) Integer status) {
        if (securityUser.getUserId().equals(userId)) {
            return ResultObjectModel.success("无法添加自己为好友！");
        }
        return linkedService.acceptLinkedMan(securityUser.getUserId(),userId,status,applyId);
    }

    @ApiOperation(value = "申请人列表", notes = "申请人列表")
    @GetMapping("/applylist")
    public ResultObjectModel getApplyList( @ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
        return linkedService.getApplyList(securityUser.getUserId());
    }


}
