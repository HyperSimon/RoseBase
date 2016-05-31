package com.roselism.base.app;

import android.app.ActivityManager;
import android.content.Context;

import com.google.common.collect.Lists;
import com.roselism.base.Process;
import com.roselism.base.collect.ConverterList;
import com.roselism.base.util.convert.RunningAppProcessInfo2Process;

import java.util.List;

/**
 * ActivityManager的增强
 * Created by simon on 16-5-29.
 */
public class ActivityManagers {

    /**
     * 获取任务管理
     *
     * @param context
     * @return
     */
    private static ActivityManager getActivityManager(Context context) {
        return (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }


    /**
     * 根据包名杀掉进程
     *
     * @param context
     * @param packageName
     */
    public static void killBackgroundProcesses(Context context, String packageName) {
        ActivityManager activityManager = getActivityManager(context);
        activityManager.killBackgroundProcesses(packageName);
    }

    public static List<Process> getRunningProcess(Context context) {
        ActivityManager manager = getActivityManager(context);
        List<ActivityManager.RunningAppProcessInfo> processes = manager.getRunningAppProcesses();
        ConverterList<Process> converterList = new ConverterList<>(Lists.<Process>newArrayList());
        RunningAppProcessInfo2Process converter = new RunningAppProcessInfo2Process(context.getPackageManager(), context);
        converterList.addAll(processes, converter);
        return converterList;
    }

    public static String availMem(Context context) {
        String avail = android.text.format.Formatter.formatFileSize(context, availMemLong(context));

        return avail;
    }


    public static long availMemLong(Context context) {
        ActivityManager activityManager = getActivityManager(context);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        return memoryInfo.availMem;
    }
}
