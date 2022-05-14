package com.example.music.Entity.Pojo.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Group {
    @ApiModelProperty(value = "组ID", example = "1",hidden = true)
    private Long groupId;
    @NotNull(message = "组名不能为空")
    @Length(max = 20,message = "组名长度不能超过20不能小于1",min = 1)
    @ApiModelProperty(value = "组名", example = "测试组")
    private String groupName;
    @Range(min = 0,max = 1,message = "组状态只能0或1 0表示正常 1表示禁言")
    @ApiModelProperty(value = "组状态", example = "1")
    private Integer groupState = 0;
    @ApiModelProperty(value = "群主id", example = "1",hidden = true)
    private Long groupOwner;
    @NotNull
    @ApiModelProperty(value = "群头像")
    private String groupImg;
    @ApiModelProperty(value = "创建时间", example = "2019-01-01 00:00:00",hidden = true)
    private Timestamp groupCreateTime = new Timestamp(System.currentTimeMillis());
    @NotNull(message = "组描述不能为空")
    @ApiModelProperty(value = "组描述", example = "测试组描述")
    private String groupDescription;
    @ApiModelProperty(value = "组号", example = "1",hidden = true)
    private Long groupNumber;
}
