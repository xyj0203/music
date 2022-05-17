package com.example.music.Entity.Pojo.Vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import static com.alibaba.excel.enums.poi.HorizontalAlignmentEnum.CENTER;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@HeadStyle(horizontalAlignment = CENTER)

public class UserVo {
    @ExcelProperty("用户id")
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ExcelProperty("用户账号")
    @ApiModelProperty(value ="用户账号")
    private String account;
    @ExcelProperty("用户邮箱")
    @ApiModelProperty(value = "用户邮箱")
    private String email;
    @ExcelProperty("性别")
    @ApiModelProperty(value = "性别")
    private String sex;
    @ExcelProperty("生日")
    @ApiModelProperty(value = "生日")
    private String birthday;
    @ExcelProperty("年龄")
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ExcelProperty("用户昵称")
    @ApiModelProperty(value = "用户昵称")
    private String username;
    @ExcelProperty("头像")
    @ApiModelProperty(value = "头像")
    private String headImg;
    @ExcelProperty("在线状态")
    @ApiModelProperty(value = "在线状态")
    private String online;
    @ExcelProperty("地区")
    @ApiModelProperty(value = "地区")
    private String area;
    @ExcelProperty("签名")
    @ApiModelProperty(value ="签名")
    private String signature;
    @ExcelProperty("等级")
    @ApiModelProperty(value = "等级")
    private String level;
}
