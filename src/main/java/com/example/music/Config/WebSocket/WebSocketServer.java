package com.example.music.Config.WebSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.music.Utils.RedisKeyUtils;
import com.example.music.Utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/message/{userId}")
@Slf4j
@Component
public class WebSocketServer {
    //统计在线的人数
    private static int onlineCount = 0;
    //存储每个客户端对应的webSocket对象
    private static ConcurrentHashMap<Long,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    //与某个客户端的会话
    private Session session;
    //用户id
    private Long userId;
    private static RedisUtils redisUtils;
    @Autowired
    public WebSocketServer(RedisUtils redisUtils){
        WebSocketServer.redisUtils = redisUtils;
    }

    public WebSocketServer() {
    }

    @OnOpen
    public void onOpen(Session session,@PathParam("userId") Long userId){
        this.session = session;
        this.userId = userId;
        if (webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
        }else{
            //将用户放入
            webSocketMap.put(userId,this);
            addOnlineCount();
        }
        redisUtils.setBit(RedisKeyUtils.ONLINE_STATE,userId,true);
        log.info("有新的连接加入，当前在线人数为："+getOnlineCount());
        try{
            sendMessage("连接成功！");
        }catch (IOException e){
            log.error("用户："+userId+"连接失败！");
        }
    }

    @OnError
    public void OnError(Session session,Throwable error){
        error.printStackTrace();
        log.info("我出错了");
    }

    @OnMessage
    public void OnMessage(String message,Session session) {
        log.info("用户："+this.userId+"发送消息："+message);
        if (StringUtils.hasLength(message)){
            try {
                JSONObject jsonObject = JSON.parseObject(message);
                jsonObject.put("fromUserId",this.userId);
                String toUserId = jsonObject.getString("privateUsers");
                if (StringUtils.hasLength(toUserId) && webSocketMap.containsKey(Long.valueOf(toUserId))){
                    webSocketMap.get(Long.valueOf(toUserId)).sendMessage(jsonObject.toJSONString());
                }else{
                    System.out.println("用户不在线！");
                    log.error("用户："+userId+"消息发送失败！");
                }
            }catch (Exception e){
                e.printStackTrace();
                log.error("用户："+userId+"消息发送失败！");
            }
        }
    }



    /**
     * 发送自定义消息
     * @param message 消息
     * @param userId  用户id
     */
    public static void sendInfo(String message, @PathParam("userId") Long userId) throws IOException {
        log.info("发送消息到:" + userId + "，报文:" + message);
        if (userId != null && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
        } else {
            log.error("用户" + userId + ",不在线！");
        }
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    @OnClose
    public void onClose(){
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            subOnlineCount();
            redisUtils.setBit(RedisKeyUtils.ONLINE_STATE,userId,false);
        }
        log.info("用户退出:" + userId + ",当前在线人数为:" + getOnlineCount());
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static synchronized  int getOnlineCount() {
        return WebSocketServer.onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

}
