package com.example.music;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class QRCode {
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static void main(String[] args) throws WriterException, IOException {
        String test = "http://www.baidu.com";
        String path = "D:/";
        int width = 900;
        int height = 900;
        String format = "png";
        Hashtable<EncodeHintType, Object> ht = new Hashtable<EncodeHintType, Object> ();
        ht.put (EncodeHintType.CHARACTER_SET, "UTF-8");
        // 设置二维码参数(编码内容，编码类型，图片宽度，图片高度,格式)
        BitMatrix bitMatrix = new MultiFormatWriter().encode (test, BarcodeFormat.QR_CODE, width, height, ht);
// 生成二维码(定义二维码输出服务器路径)
        File outputFile = new File (path);
        if (!outputFile.exists ()){
            //创建文件夹
            outputFile.mkdir ();
        }
        int b_width = bitMatrix.getWidth ();
        int b_height = bitMatrix.getHeight ();
        // 建立图像缓冲器
        BufferedImage image = new BufferedImage (b_width, b_height, BufferedImage.TYPE_3BYTE_BGR);
        for ( int x = 0; x < b_width; x++ )
        {
            for ( int y = 0; y < b_height; y++ )
            {
                image.setRGB (x, y, bitMatrix.get (x, y) ? BLACK : WHITE);
            }
        }
        // 生成二维码
        ImageIO.write (image, format, new File (path + "/erweima." + format)); //二维码的名称 是 erweima.sgif
    }
}
