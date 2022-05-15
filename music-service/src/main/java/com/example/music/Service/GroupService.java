package com.example.music.Service;

import com.example.music.Entity.Pojo.Entity.Group;
import com.example.music.Entity.Pojo.ResultObjectModel;
import org.springframework.web.multipart.MultipartFile;

public interface GroupService {
    /**
     * 创建群组
     * @param group
     * @param file
     * @return
     */
    ResultObjectModel createGroup(Group group, MultipartFile file);
}
