package com.example.music.Controller.BasicController;

import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Service.MusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(tags = "未登录音乐操作")
@RestController
@RequestMapping("/Basic/music")
@Validated
public class NormalMusicController {

    @Autowired
    private MusicService musicService;

    @ApiOperation("获取音乐分类列表")
    @GetMapping("/getMusicTypeList")
    @ApiImplicitParam(name = "type", value = "类型 0语种 1风格 2场景 3情感 4主题", required = true, dataType = "int", paramType = "query")
    public ResultObjectModel getClassifyList(@Valid @NotNull @Range(max = 4,min = 0) Integer type){
        return musicService.getClassifyList(type);
    }


}
