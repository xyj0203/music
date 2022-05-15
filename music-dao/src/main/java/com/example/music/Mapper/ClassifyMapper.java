package com.example.music.Mapper;

import com.example.music.Entity.Pojo.Entity.MusicType;

import java.util.List;

public interface ClassifyMapper {

    /**
     * 添加情感分类
     * @param emotion
     * @return
     */
    Integer addEmotion(String emotion);

    /**
     * 添加语言分类
     * @param language
     * @return
     */
    Integer addLanguage(String language);

    /**
     * 添加场景分类
     * @param scene
     * @return
     */
    Integer addScene(String scene);

    /**
     * 添加主题分类
     * @param theme
     * @return
     */
    Integer addTheme(String theme);

    /**
     * 添加风格分类
     * @param genre
     * @return
     */
    Integer addGenre(String genre);

    /**
     * 获取分类
     * @return
     */
    List<MusicType> getLanguageList();
    List<MusicType> getGenreList();
    List<MusicType> getSceneList();
    List<MusicType> getEmotionList();
    List<MusicType> getThemeList();

    /**
     * 删除
     * @param id
     * @return
     */
    int delLanguage(Integer id);
    int delGenre(Integer id);
    int delScene(Integer id);
    int delEmotion(Integer id);
    int delTheme(Integer id);
}
