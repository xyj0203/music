package com.example.music.Service;

import com.example.music.Entity.Pojo.ResultObjectModel;

import java.util.List;

public interface MessageService {

    /**
     * 发送个人消息
     * @param userId
     * @param receiverId
     * @param content
     * @param type
     * @return
     */
    ResultObjectModel sendPersonMessage(Long userId, Long receiverId, String content, Integer type);

    /**
     * 标记消息已读
     * @param userId
     * @param messageIds
     * @return
     */
    ResultObjectModel markMessageAsRead(Long userId, List<Long> messageIds);

    /**
     * 获取未读消息
     * @param userId
     * @return
     */
    ResultObjectModel getUnreadMessage(Long userId);
}
