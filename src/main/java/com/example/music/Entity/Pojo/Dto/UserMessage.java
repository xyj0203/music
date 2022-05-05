package com.example.music.Entity.Pojo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserMessage {
    private Long messageId;
    private Long sendId;
    private Long recieverId;
    private String content;
    private String sendTime;
    private Integer is_read;
    private Integer type;
    private Integer messageState;
}
