package com.example.music.Utils;

import java.security.SecureRandom;
import java.util.Random;

public class TextUtils {
    private static final byte[] CODE_BASE = "abcdefghijklmnopqrstuvwxyz-ABCDEFGHIJKLMNOPQRSTUVWXYZ-0123456789".getBytes();
    private static final byte[] hexMap = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};

    private TextUtils() {
        throw new IllegalStateException();
    }

    public static String generateRandomCode(int length) {
        Random random = new SecureRandom();
        if (length < 1) {
            throw new IllegalArgumentException("length must a positive number");
        } else {
            byte[] codes = new byte[length];

            for(int i = 0; i < length; ++i) {
                codes[i] = CODE_BASE[random.nextInt(CODE_BASE.length)];
            }

            return new String(codes);
        }
    }

    public static String bytesToHex(byte[] src) {
        byte[] strArr = new byte[src.length << 1];
        int i = 0;
        byte[] var4 = src;
        int var5 = src.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            byte b = var4[var6];
            int t = b & 255;
            strArr[i++] = hexMap[t >> 4];
            strArr[i++] = hexMap[t & 15];
        }

        return new String(strArr);
    }
}
