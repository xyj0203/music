package com.example.music.Controller.UserController;

import com.example.music.Entity.Pojo.Entity.Group;
import com.example.music.Entity.Pojo.Entity.SecurityUser;
import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Service.GroupService;
import com.example.music.Utils.CompontUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Api(tags = "群组接口")
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Resource
    private CompontUtil compontUtil;

    @ApiOperation(value = "获取群组列表")
    @GetMapping("/list")
    public ResultObjectModel getGroupList() {
        return null;
    }

    @ApiOperation(value = "获取群组详情")
    @GetMapping("/detail")
    public ResultObjectModel getGroupDetail() {
        return null;
    }

    @ApiOperation(value = "创建群组")
    @PostMapping("/create")
    public ResultObjectModel createGroup(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,
                                         @NotNull Group group,
                                         @NotNull MultipartFile file) {
        group.setGroupOwner(securityUser.getUserId());
        group.setGroupNumber(compontUtil.getRandomLong(1000000000L, 99999999999L));
        System.out.println(group);
        System.out.println(groupService);
        return groupService.createGroup(group, file);
    }

    @ApiOperation(value = "删除群组")
    @DeleteMapping ("/delete")
    public ResultObjectModel deleteGroup() {
        return null;
    }

    @ApiOperation(value = "修改群组")
    @PutMapping("/update")
    public ResultObjectModel updateGroup() {
        return null;
    }

    @ApiOperation(value = "加入群组")
    @PostMapping("/join")
    public ResultObjectModel joinGroup() {
        return null;
    }

    @ApiOperation(value = "退出群组")
    @DeleteMapping ("/exit")
    public ResultObjectModel exitGroup() {
        return null;
    }

    @ApiOperation(value = "获取群组成员")
    @GetMapping("/member")
    public ResultObjectModel getGroupMember() {
        return null;
    }

    @ApiOperation(value = "获取群组消息")
    @GetMapping("/message")
    public ResultObjectModel getGroupMessage() {
        return null;
    }

    @ApiOperation(value = "发送群组消息")
    @PostMapping("/send")
    public ResultObjectModel sendGroupMessage() {
        return null;
    }

    @ApiOperation(value = "删除群组消息")
    @DeleteMapping("/deleteMessage")
    public ResultObjectModel deleteGroupMessage() {
        return null;
    }

    @ApiOperation(value = "获取群组公告")
    @GetMapping("/notice")
    public ResultObjectModel getGroupNotice() {
        return null;
    }
}
