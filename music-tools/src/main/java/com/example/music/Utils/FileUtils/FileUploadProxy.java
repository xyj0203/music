package com.example.music.Utils.FileUtils;

import com.example.music.Entity.CustomzieException;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "upload")
@Component(value = "fileUploadProxy")
public class FileUploadProxy implements ApplicationContextAware {

    //对应的类型允许上传的
    private Map<String, List<String>> fileMap;
    //注入对应的实例
    private ApplicationContext act;


    public List<String> upload(MultipartFile ...files) throws IOException {
        if (checkFileIsEmpty(files)) {
            throw new CustomzieException("部分文件为空!");
        }
        List<String> list = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            byte []buffers = multipartFile.getBytes();
            String fileName = multipartFile.getOriginalFilename();
            String extName = StringUtils.getFilenameExtension(fileName);
            //根据文件的拓展名不同，上传到不同的文件服务器上
            for (Map.Entry<String, List<String>> entry : fileMap.entrySet()) {
                List<String> suffixList = entry.getValue();
                for (String suffix : suffixList) {
                    if (extName.equalsIgnoreCase(suffix)) {
                        String key = entry.getKey();
                        list.add( act.getBean(key, FileUpload.class).upload(buffers,extName));
                    }
                }
            }
        }
        return list;
    }




    public void setFileMap(Map<String, List<String>> fileMap) {
        this.fileMap = fileMap;
    }


    //注入容器
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        act = applicationContext;
    }

    private static boolean checkFileIsEmpty(MultipartFile ...files) {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
