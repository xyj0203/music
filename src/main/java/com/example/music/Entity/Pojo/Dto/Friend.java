package com.example.music.Entity.Pojo.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Friend {
    private Long id;
    private Long fId;
    private Long friendId;
    private Long userId;
    private String fName;
}
