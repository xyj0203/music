package com.example.music.Service.ServiceImpl;

import com.example.music.Entity.Pojo.Entity.MusicType;
import com.example.music.Entity.Pojo.ResultObjectModel;
import com.example.music.Mapper.ClassifyMapper;
import com.example.music.Service.MusicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {

    @Resource
    private ClassifyMapper classifyMapper;

    @Override
    public ResultObjectModel addClassify(String content, Integer type) {
        int i = -1;
        switch (type){
            case 0:
                i = classifyMapper.addLanguage(content);
                break;
            case 1:
                i = classifyMapper.addGenre(content);
                break;
            case 2:
                i = classifyMapper.addScene(content);
                break;
            case 3:
                i = classifyMapper.addEmotion(content);
                break;
            case 4:
                i = classifyMapper.addTheme(content);
                break;
        }
        if (i == 1){
            return ResultObjectModel.success( "添加成功");
        }
        return ResultObjectModel.fail( "添加失败");
    }

    @Override
    public ResultObjectModel getClassifyList(Integer type) {
        List<MusicType> list = null;
        switch (type){
            case 0:
                list = classifyMapper.getLanguageList();
                break;
            case 1:
                list = classifyMapper.getGenreList();
                break;
            case 2:
                list = classifyMapper.getSceneList();
                break;
            case 3:
                list = classifyMapper.getEmotionList();
                break;
            case 4:
                list = classifyMapper.getThemeList();
                break;
        }
        if (list != null){
            return ResultObjectModel.success(list);
        }
        return ResultObjectModel.fail( "获取失败");
    }

    @Override
    public ResultObjectModel deleteClassify(Integer id, Integer type) {
        int i = -1;
        switch (type){
            case 0:
                i = classifyMapper.delLanguage(id);
                break;
            case 1:
                i = classifyMapper.delGenre(id);
                break;
            case 2:
                i = classifyMapper.delScene(id);
                break;
            case 3:
                i = classifyMapper.delEmotion(id);
                break;
            case 4:
                i = classifyMapper.delTheme(id);
                break;
        }
        if (i == 1){
            return ResultObjectModel.success( "删除成功！");
        }
        return ResultObjectModel.fail( "删除失败！");
    }
}
