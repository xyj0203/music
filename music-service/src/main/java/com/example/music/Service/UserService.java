package com.example.music.Service;

import com.example.music.Entity.Pojo.ResultObjectModel;

public interface UserService {

    /**
     *管理员修改密码
     * @param userId
     * @param newPassword
     * @return
     */
    ResultObjectModel updatePassword(Long userId, String newPassword);
}
