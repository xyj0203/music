package com.example.music.Entity.Pojo.Vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApplyVo {
    @ApiModelProperty(value = "申请id")
    private Long applyId;
    @JsonIgnore
    @ApiModelProperty(value = "申请时间",hidden = true)
    private Timestamp applyTime;
    @ApiModelProperty(value = "申请时间")
    private String applyTimeStr;
    @ApiModelProperty(value = "验证信息")
    private String sendContent;
    @ApiModelProperty(value = "申请状态")
    @JsonIgnore
    private Integer applyState = 0;
    @ApiModelProperty(value = "申请状态描述")
    private String applyStateStr;
    @ApiModelProperty(value = "申请者id")
    private Long applyUserId;
    @ApiModelProperty(value = "申请者账号")
    private String applyUserAccount;
    @ApiModelProperty(value = "申请者头像")
    private String applyUserHeadImg;
    @ApiModelProperty(value = "申请者昵称")
    private String applyUserUsername;
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Integer applyUserSexValue;
    @ApiModelProperty(value = "申请者性别")
    private String applyUserSex;
    @JsonIgnore
    @ApiModelProperty(value = "申请者生日",hidden = true)
    private Date applyUserBirthdayTime;
    @ApiModelProperty(value = "申请者年龄")
    private Integer applyUserAge;
}
