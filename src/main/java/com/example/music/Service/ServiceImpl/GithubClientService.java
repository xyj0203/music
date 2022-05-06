package com.example.music.Service.ServiceImpl;

import com.example.music.Entity.Pojo.ResultObjectModel;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class GithubClientService {

    private static final String CLIENT_ID = "d011264c4fd07f6ad826";
    private static final String CLIENT_SECRET = "77a5ec1427107d2176cb4f067197c0ac9a093a69";
    private static final String REDIRECT_URI = "http://localhost:8081/music/Basic/authorization_code";
    private static final String  GITHUB_HREF = "https://github.com/login/oauth/authorize";
    private static final String  state = "1234";

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, String> getAccessToken(String code) {
        Map<String, String> map = new HashMap<>();
        map.put("client_id", CLIENT_ID);
        map.put("client_secret", CLIENT_SECRET);
        map.put("code", code);
        map.put("redirect_uri", REDIRECT_URI);
        Map<String, String> result = restTemplate.postForObject("https://github.com/login/oauth/access_token", map, Map.class);
        return result;
    }

    //获取用户信息
    public Map<String, Object> queryUser(String accessToken){
        HttpHeaders httpheaders = new HttpHeaders();
        httpheaders.add("Authorization", "token " + accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(httpheaders);
        ResponseEntity<Map> exchange = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, httpEntity, Map.class);
        System.out.println("exchange.getBody() = " + exchange.getBody());
        return exchange == null ? null : exchange.getBody();
    }
}
