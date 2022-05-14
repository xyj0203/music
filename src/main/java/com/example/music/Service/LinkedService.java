package com.example.music.Service;

import com.example.music.Entity.Pojo.ResultObjectModel;

public interface LinkedService {

    /**
     * 判断是否是联系人
     * @return
     */
    public boolean isLinked(Long userId,Long friendId);

    /**
     * 查询联系人
     * @param account
     * @return
     */
    ResultObjectModel searchLinkedMan(String account);

    /**
     * 添加好友
     * @param userId
     * @param friendId
     * @return
     */
    ResultObjectModel addLinkedMan(Long userId, Long friendId,String sendContent);

    /**
     * 修改好友状态
     * @param userId
     * @param friendId
     * @param status
     * @return
     */
    ResultObjectModel acceptLinkedMan(Long userId, Long friendId, Integer status,Long applyId);

    /**
     * 查询申请人列表
     * @param userId
     * @return
     */
    ResultObjectModel getApplyList(Long userId);

    /**
     * 获取联系人列表
     * @param userId
     * @return
     */
    ResultObjectModel getLinkedManList(Long userId);

    /**
     * 删除联系人
     * @param userId
     * @return
     */
    ResultObjectModel deleteLinkedMan(Long userId);
}
