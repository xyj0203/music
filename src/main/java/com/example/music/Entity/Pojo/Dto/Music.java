package com.example.music.Entity.Pojo.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Music {
    @ApiModelProperty(value = "音乐id", name = "musicId", example = "1")
    private Long musicId;
    @ApiModelProperty(value = "音乐名称", name = "musicName", example = "爱的故事")
    private String musicName;
    @ApiModelProperty(value = "歌手",name = "singerId",example = "1")
    private Long singerId;
    @ApiModelProperty(value = "上传时间",name = "uploadTime",example = "2019-01-01")
    private Date musicUploadTime = new Date(System.currentTimeMillis());
    @ApiModelProperty(value = "封面图",name = "musicCoverSrc")
    private String musicCoverSrc;
    @ApiModelProperty(value = "音乐文件",name = "musicFileSrc")
    private String musicFileSrc;
    @ApiModelProperty(value = "音乐歌词文件",name = "lyricId")
    private Long lyricId;
    @ApiModelProperty(value = "音乐播放次数",name = "musicPlayCount")
    private Long musicPlayCount;
    @ApiModelProperty(value = "音乐评论数",name = "musicCommentCount")
    private Long musicCommentCount;
    @ApiModelProperty(value = "音乐收藏数",name = "musicCollectCount")
    private Long musicCollectCount;
    @ApiModelProperty(value = "音乐下载数",name = "musicDownloadCount")
    private Long musicDownloadCount;
}
