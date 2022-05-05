package com.example.music.Service;

import com.example.music.Entity.Pojo.ResultObjectModel;

public interface MusicService {

    /**
     * 添加分类类别
     * @param content
     * @param type
     * @return
     */
    ResultObjectModel addClassify(String content, Integer type);

    /***
     * 获取音乐分类列表
     * @param type
     * @return
     */
    ResultObjectModel getClassifyList(Integer type);

    /**
     * 删除分类
     * @param id
     * @param type
     * @return
     */
    ResultObjectModel deleteClassify(Integer id, Integer type);
}
