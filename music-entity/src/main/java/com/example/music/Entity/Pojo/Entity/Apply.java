package com.example.music.Entity.Pojo.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class Apply {
    @ApiModelProperty(value = "申请id")
    private Long applyId;
    @ApiModelProperty(value = "申请者id")
    private Long applyUserId;
    @ApiModelProperty(value = "被申请者id")
    private Long receiverUserId;
    @ApiModelProperty(value = "申请时间")
    private Timestamp applyTime = new Timestamp(System.currentTimeMillis());
    @ApiModelProperty(value = "验证信息")
    private String sendContent;
    @ApiModelProperty(value = "申请状态")
    private Integer applyState = 0;

    public Apply(Long applyId, Long applyUserId, Long receiverUserId,String sendContent) {
        this.applyId = applyId;
        this.applyUserId = applyUserId;
        this.receiverUserId = receiverUserId;
        this.sendContent = sendContent;
    }
}
