package com.example.music.Controller.BasicController;

import com.example.music.Config.GithubClient.GithubClient;
import com.example.music.Entity.CustomzieException;
import com.example.music.Entity.Pojo.Entity.Img;
import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Mapper.BasicMapper;
import com.example.music.Service.BasicService;
import com.example.music.Service.LinkedService;
import com.example.music.Service.ServiceImpl.GithubClientService;
import com.example.music.Utils.CompontUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Api(tags = "基础功能")
@RestController
@RequestMapping("/Basic")
@Validated
public class BasicController {

    @Autowired
    private CompontUtil componntUtil;
    @Autowired
    private BasicService basicService;
    @Autowired
    private GithubClient githubClient;
    @Autowired
    private GithubClientService githubClientService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LinkedService linkedService;
    @Autowired
    private BasicMapper basicMapper;

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

    @GetMapping("/authorization_code")
    public void authorization_code(@ApiIgnore HttpServletRequest request,@ApiIgnore HttpServletResponse response, String code,String state) throws ServletRequestBindingException, IOException {
        System.out.println("code:"+code);
        System.out.println("state:"+state);
        if(!githubClient.isStateSupported(state)){
            throw new CustomzieException("state参数不合法");
        }

        //github登录验证，并获取access_token
        Map<String,String> resp = githubClientService.getAccessToken(code);
        System.out.println(resp.get("access_token"));
        //跳转本系统的登录流程，获取用户信息，实现两个系统用户的对接
        String url = "http://localhost:8081/music/Basic/login";
        this.sendByPost(response, url,resp.get("access_token"),"2");//this.sendByPost(response, url,"access_token","github");
    }

    public void sendByPost(HttpServletResponse response, String url, String principal, String authType) throws IOException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println(" <HEAD><TITLE>Post 方法</TITLE></HEAD>");
        out.println(" <BODY>");
        out.println("<form name=\"submitForm\" action=\"" + url + "\" method=\"post\">");
        out.println("<input type=\"hidden\" name=\"principal\" value=\"" + principal + "\"/>");
        out.println("<input type=\"hidden\" name=\"credentials\" value=\"" + principal + "\"/>");
        out.println("<input type=\"hidden\" name=\"loginType\" value=\"" + authType + "\"/>");
        out.println("</from>");
        out.println("<script>window.document.submitForm.submit();</script> ");
        out.println(" </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

    @ApiOperation("生成Github的链接")
    @GetMapping("/getGithubUrl")
    public ResultObjectModel gernerateGithubHref(){
        return ResultObjectModel.success("请求成功",githubClient.getLoginLink());
    }

    @ApiOperation("瀑布流获取数据")
    @GetMapping("/getImg")
    public ResultObjectModel getImg(Integer pageNow,Integer pageSize,String name){
        System.out.println("pageNow:"+pageNow);
        System.out.println("pageSize:"+pageSize);
        PageHelper.startPage(pageNow,pageSize);
        List<Img> imgs = basicMapper.getImg(name);
        System.out.println(imgs.size());
        PageInfo<Img> pageInfo = new PageInfo<>(imgs);
        System.out.println(pageInfo.getTotal());
        return ResultObjectModel.success("请求成功",pageInfo);
    }

    @ApiOperation("获取联系人详情")
    @ApiImplicitParam(name = "account",value = "联系人账号",required = true,dataType = "String",paramType = "query")
    @GetMapping("/searchLinkedMan")
    public ResultObjectModel searchLinkedMan(@NotNull String account) {
        if (account.trim().isEmpty()) {
            return ResultObjectModel.success("请输入账号！");
        }
        return linkedService.searchLinkedMan(account);
    }
}
