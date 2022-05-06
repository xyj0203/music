package com.example.music.Mapper;

import com.example.music.Entity.Pojo.Entity.Apply;
import com.example.music.Entity.Pojo.Entity.User;
import com.example.music.Entity.Pojo.Vo.ApplyVo;
import com.example.music.Entity.Pojo.Vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LinkedMapper {
    /**
     * 查询两个人是否有好友关系
     * @param userId
     * @param friendId
     * @return
     */
    int isLinked(@Param("userId") Long userId, @Param("friendId") Long friendId);

    /**
     * 申请添加好友
     * @param apply
     * @return
     */
    int addLinkedMan(Apply apply);

    /**
     * 查询申请人列表
     * @param userId
     * @return
     */
    List<ApplyVo> queryApplyList(Long userId);

    /**
     * 修改申请状态
     * @param applyId
     * @param status
     * @return
     */
    int updateApplyStatus(@Param("applyId") Long applyId,@Param("state") Integer status);

    /**
     * 添加为好友
     * @param userId
     * @param friendId
     * @return
     */
    int addLinkedMantogether(@Param("userId") Long userId,@Param("friendId") Long friendId);


    /**
     * 查询联系人列表
     * @param userId
     * @return
     */
    List<User> queryLinkedManList(Long userId);
}
