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
public class Group {
    private Long groupId;
    private String groupName;
    private Integer numberFlag;
    private Integer groupState;
    private Long groupOwner;
    private String groupImg;
    private Timestamp createTime;
    private String groupDecription;
    private String groupNumber;
}
