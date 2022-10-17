package com.example.music.Utils.FileUtils;

import org.springframework.stereotype.Component;

@Component(value = "aliYunOSSFileUpload")
public class AliYunOSSFileUpload implements FileUpload{
    @Override
    public String upload(byte[] array, String exrName) {
        return null;
    }
}
