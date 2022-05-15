package com.example.music.Entity.Pojo.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserVo {
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value ="用户账号")
    private String account;
    @ApiModelProperty(value = "用户邮箱")
    private String email;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "生日")
    private String birthday;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "用户昵称")
    private String username;
    @ApiModelProperty(value = "头像")
    private String headImg;
    @ApiModelProperty(value = "在线状态")
    private String online;
    @ApiModelProperty(value = "地区")
    private String area;
    @ApiModelProperty(value ="签名")
    private String signature;
    @ApiModelProperty(value = "等级")
    private String level;
}
