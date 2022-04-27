package com.example.music.Config.Security.Filter;

import cn.hutool.core.lang.Validator;
import com.example.music.Config.Security.Token.EmailAuthenticationToken;
import com.example.music.Config.Security.Token.GitubAuthenticationToken;
import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ThirdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    //仅支持Post请求
    private boolean PostOnly = true;
    private RedisUtils redisUtils;

    public ThirdAuthenticationFilter(RedisUtils redisUtils) {
        super(new AntPathRequestMatcher("/Basic/login", "POST"));
        this.redisUtils = redisUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (PostOnly && !request.getMethod().equals("POST")) {
            ResultObjectModel.returnvalue(request, response,ResultObjectModel.fail("Only POST requests are supported"));
            return null;
        }
        //获取第三方登录的类型
        String loginType = request.getParameter("loginType");
        if (loginType == null){
            ResultObjectModel.returnvalue(request, response,ResultObjectModel.fail("登录类型不能为空"));
            return null;
        }
        int type = 0;
        try {
           type = Integer.parseInt(loginType);
        }catch (NumberFormatException e) {
            ResultObjectModel.returnvalue(request, response,ResultObjectModel.fail("登录类型不正确"));
            return null;
        }
        String principal = request.getParameter("principal");
        String credentials = request.getParameter("credentials");
        if (Validator.isEmpty(principal) || Validator.isEmpty(credentials)) {
            ResultObjectModel.returnvalue(request, response,ResultObjectModel.fail("参数信息不能为空"));
            return null;
        }
        AbstractAuthenticationToken authRequest = null;
        switch (type) {
            case 1:
                authRequest = new EmailAuthenticationToken(principal, credentials);
                String judge = judge(principal, credentials, type);
                if(judge != null){
                    ResultObjectModel.returnvalue(request, response,ResultObjectModel.fail(judge));
                    return null;
                }
                break;
            case 2:
                authRequest = new GitubAuthenticationToken(principal, credentials);
                break;
            default:
                authRequest = new UsernamePasswordAuthenticationToken(principal, credentials);
                break;
        }
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private String judge(String principal, String credentials,Integer type){
        if (type == 1){
            if (Validator.isEmail(principal)){
                if(credentials.length() == 6){
                    String scode = (String)redisUtils.get(principal);
                    if (scode != null && scode.equals(credentials)){
                        return null;
                    }else{
                        return "验证码错误";
                    }
                }else{
                    return "验证码长度不正确";
                }
            }else{
                return "邮箱格式不正确";
            }
        }
        return null;
    }

    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ResultObjectModel.returnvalue(request, response,ResultObjectModel.success("登录成功！"));
    }
}
