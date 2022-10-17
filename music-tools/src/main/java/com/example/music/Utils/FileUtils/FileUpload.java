package com.example.music.Utils.FileUtils;

/**
 * 文件上传的抽象接口
 */
public interface FileUpload {

    /**
     * 文件上传
     * @return
     */
    String upload(byte []array,String exrName);
}
