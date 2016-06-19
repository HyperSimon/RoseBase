package com.roselism.rosebase.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by simon on 16-5-28.
 */
public class MD5 {

    private static final String TAG = "MD5";

    public static String getMd5(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                messageDigest.update(bytes, 0, len);
            }

            byte[] digest = messageDigest.digest();
            for (byte b : digest) {
                int result = b & 0xff;
                String hexString = Integer.toHexString(result);
                stringBuilder.append(hexString);
                if (hexString.length() == 1) {
                    stringBuilder.append(0);
                }
            }

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
