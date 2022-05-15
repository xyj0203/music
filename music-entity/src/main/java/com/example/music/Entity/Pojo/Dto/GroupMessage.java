package com.example.music.Entity.Pojo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupMessage {
    private Long gmId;
    private String gmContent;
    private Long gmSender;
    private Integer gmType;
    private Timestamp gmTime;
    private Long groupId;
}
