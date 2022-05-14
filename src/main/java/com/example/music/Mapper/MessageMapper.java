package com.example.music.Mapper;

import com.example.music.Entity.Pojo.Dto.UserMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {

    /**
     * 持久化消息至数据库
     * @param userMessage
     * @return
     */
    int insert(UserMessage userMessage);

    /**
     * 批量标记消息已读
     * @param userId
     * @param messageIds
     * @return
     */
    int markMessageAsRead(@Param("userId") Long userId, @Param("messageIds") List<Long> messageIds);
}
