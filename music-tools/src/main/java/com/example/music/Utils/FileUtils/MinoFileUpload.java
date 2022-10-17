package com.example.music.Utils.FileUtils;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.stereotype.Component;

@Component(value = "minoFileUpload")
public class MinoFileUpload implements FileUpload{

    private static String secretId = "XUYUJIE";
    private static String secretKey = "XIEXIAOJIA";
    private static String gateway = "http://www.xyjxkl.top:9000";
    private static MinioClient minioClient;
    private static String bucketName = "xiexiaojia";

    static {
        try {
            minioClient = new MinioClient(gateway, secretId, secretKey);
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String upload(byte[] array, String exrName) {
        return null;
    }
}
