package com.roselism.base.util;

import android.content.Context;

import com.roselism.base.util.convert.InStream2OutStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件私有空间的工具
 * simon on 16-5-22.
 */
public class PrivateStoreUtil {

//    public void writeIn(Context context) {
//        new Thread(() -> {
//            InputStream inputStream;
//            try {
//                inputStream = context.getResources().getAssets().open("commonnum.db");
//                File file = new File(context.getFilesDir(), "commonnum.db");
//                InStream2OutStream outStream = new InStream2OutStream(file);
//                outStream.convert(inputStream);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
}
