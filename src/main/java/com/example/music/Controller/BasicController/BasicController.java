package com.example.music.Controller.BasicController;

import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Service.BasicService;
import com.example.music.Utils.CompontUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Api(tags = "基础功能")
@RestController
@RequestMapping("/Basic")
@Validated
public class BasicController {

    @Autowired
    private CompontUtil componntUtil;
    @Autowired
    private BasicService basicService;

    @ApiOperation(value = "获取邮箱验证码", notes = "获取邮箱验证码")
    @PostMapping("/getEmailCode")
    @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "query")
    public ResultObjectModel getEmailCode(@Valid @NotNull(message = "不能为空") @Email(message = "必须为邮箱") String email) {
        String code = componntUtil.sendEmail(email);
        return code == null ? ResultObjectModel.fail( "获取邮箱验证码失败") : ResultObjectModel.success("获取验证码成功");
    }

    @ApiOperation(value = "注册", notes = "使用邮箱注册")
    @PostMapping("/register")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = "account", value = "账号", required = true, dataType = "String", paramType = "query")
            }
    )
    public ResultObjectModel register(@Valid @NotNull(message = "邮箱不能为空") @Email(message = "必须为邮箱")String email,
                                      @NotNull(message = "验证码不能为空") @Length(min = 6,max = 6,message = "验证码长度不符合") String code,
                                      @NotNull(message = "账号不能为空") @Pattern(regexp = "^[A-Za-z_]{1}[A-Za-z\\d_]{5,19}$",message = "用户名不合法,长度6-20位，由数字，字母，下划线组成，不能以数字开头") String account,
                                      @NotNull(message = "密码不能为空") @Pattern(regexp = "^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?![_]+$)[0-9_A-Za-z]{7,19}$",message = "密码长度为8-20位，由数字，字母，下划线组成，且至少包含两种及以上字符，字母区分大小写") String password) {
        return basicService.register(email, code, password, account);
    }

    @ApiOperation(value = "登录", notes = "使用邮箱登录")
    @PostMapping("/login")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "principal", value = "认证", required = true, dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = "credentials", value = "密码", required = true, dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = "loginType",value = "登录类型 1邮箱 2github 3账号密码",required = true,dataType = "int",paramType = "query")
            }
            )
    public ResultObjectModel login(String principal, String credentials,Integer loginType) {
        return null;
    }
}
