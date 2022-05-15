package com.example.music.Entity.Pojo.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
public class UserMessage implements Serializable {
    @ApiModelProperty(value = "消息id")
    private Long messageId;
    @ApiModelProperty(value = "发送者id")
    private Long sendId;
    @ApiModelProperty(value = "接收者id")
    private Long recieverId;
    @ApiModelProperty(value = "消息内容")
    private String content;
    @ApiModelProperty(value = "发送时间")
    private Timestamp sendTime;
    @ApiModelProperty(value = "消息是否已读 0 未读 1 已读")
    private Integer isRead = 0;
    @ApiModelProperty(value = "消息类型 0文字  1图片 2音频 3视频")
    private Integer type;
    @ApiModelProperty(value = "消息的状态发送状态 0未发送 1已发送 2发送失败")
    private Integer messageState = 0;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public Long getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(Long recieverId) {
        this.recieverId = recieverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMessageState() {
        return messageState;
    }

    public void setMessageState(Integer messageState) {
        this.messageState = messageState;
    }
}
