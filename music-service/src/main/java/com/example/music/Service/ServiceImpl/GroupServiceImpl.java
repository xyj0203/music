package com.example.music.Service.ServiceImpl;

import com.example.music.Entity.Pojo.Entity.Group;
import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GroupServiceImpl implements GroupService {

    @Override
    public ResultObjectModel createGroup(Group group, MultipartFile file) {

        return null;
    }
}
