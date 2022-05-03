package com.example.music.Config.GithubClient;

import com.example.music.Utils.TextUtils;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class GithubClient {
    private static final String CLIENT_ID = "d011264c4fd07f6ad826";
    private static final String CLIENT_SECRET = "77a5ec1427107d2176cb4f067197c0ac9a093a69";
    private static final String REDIRECT_URI = "http://localhost:8081/music/Basic/authorization_code";
    private static final String  GITHUB_HREF = "https://github.com/login/oauth/authorize";
    private final ConcurrentHashMap<String, String> states = new ConcurrentHashMap<>();


    public boolean isStateSupported(String state) {
        Objects.requireNonNull(state, "state must not be null");
        if (!this.states.containsKey(state)) {
            return false;
        }
        this.states.remove(state);
        return true;
    }

    public String getLoginLink() {
        String state = TextUtils.generateRandomCode(16);
        String url = GITHUB_HREF + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&scope=user&state=" + state;
        this.states.put(state, url);
        return url;
    }


}
