package com.example.music.Controller.UserController;

import com.example.music.Entity.Pojo.Entity.SecurityUser;
import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "消息接口")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation("发送个人消息")
    @PostMapping("/sendPersonMessage")
    public ResultObjectModel sendPersonMessage(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,
                                         Long receiverId,
                                         String content,
                                         Integer type){

        return null;
    }

    @ApiOperation("发送群组消息")
    @PostMapping("/sendGroupMessage")
    public ResultObjectModel sendGroupMessage(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,
                                              Long groupId,String content,Integer type)
    {
        return null;
    }

    @ApiOperation("获取个人消息")
    @PostMapping("/getPersonMessage")
    public ResultObjectModel getPersonMessage(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,
                                              Integer page,Integer size){
        return null;
    }

    @ApiOperation("获取群组消息")
    @PostMapping("/getGroupMessage")
    public ResultObjectModel getGroupMessage(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,
                                             Long groupId,Integer page,Integer size){
        return null;
    }

    @ApiOperation("获取未读消息")
    @PostMapping("/getUnreadMessage")
    public ResultObjectModel getUnreadMessage(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser){
        return null;
    }

    @ApiOperation("获取未读消息数量")
    @PostMapping("/getUnreadMessageCount")
    public ResultObjectModel getUnreadMessageCount(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser){
        return null;
    }

}
