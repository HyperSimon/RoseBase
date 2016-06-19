package com.roselism.rosebase.util;

import android.content.Context;
import android.os.Environment;
import android.text.format.Formatter;

/**
 * 关于储存的工具类
 *
 * @author: Hyper Simon Wang
 * @create_time: 2016/05/23 09:02
 * @packageName: com.roselism.base.util
 */
public class Storager {

    /**
     * 获取机身储存大小
     *
     * @param context
     * @return
     */
    public static String getInternalFreeSize(Context context) {
        long totalSpace = Environment.getDataDirectory().getTotalSpace();
        return formatFileSize(context, totalSpace);
    }


    public static float getInternalFreeSize() {
        return Environment.getDataDirectory().getTotalSpace();
    }


    /**
     * 格式化储存大小
     *
     * @param context
     * @param totalSpace
     * @return
     */
    private static String formatFileSize(Context context, long totalSpace) {
        return Formatter.formatFileSize(context, totalSpace);
    }

    /**
     * 外部SD卡可用空间
     *
     * @param context 上下文对象
     * @return 外部储存设备的可用空间
     */
    public static String getExternalFreeSize(Context context) {
        long freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
        return formatFileSize(context, freeSpace);
    }

    /**
     * 外部SD卡可用空间
     *
     * @return 外部储存设备的可用空间
     */
    public static float getExternalFreeSize() {
        return Environment.getExternalStorageDirectory().getFreeSpace();
    }

    public static float getInternalTotalSize() {
        return Environment.getDataDirectory().getTotalSpace();
    }

    public static float getExternalTotalSize() {
        return Environment.getExternalStorageDirectory().getTotalSpace();
    }
}
