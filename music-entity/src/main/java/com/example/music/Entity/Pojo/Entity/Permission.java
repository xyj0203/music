package com.example.music.Entity.Pojo.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Permission {
    @TableId
    @ApiModelProperty(value = "权限id",hidden = true)
    private Long permissionId;
    @ApiModelProperty("权限形容")
    @NotNull
    @Length(min = 1,message = "权限形容不能为空")
    private String description;
    @ApiModelProperty("权限路径")
    @NotNull
    @Length(min = 1,message = "权限路径不能为空")
    private String path;
    @NotNull
    @Length(min = 1,message = "权限名称不能为空")
    @ApiModelProperty("权限名称")
    private String name;
}
