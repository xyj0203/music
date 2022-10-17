package com.example.music.Utils.FileUtils;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "fastDFSFileUpload")
public class FastDFSFileUpload implements FileUpload{

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Override
    public String upload(byte[] array, String exrName) {
        return null;
    }
}
