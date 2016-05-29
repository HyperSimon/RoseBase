package com.roselism.base.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.google.common.collect.Lists;
import com.roselism.base.Application;
import com.roselism.base.Process;
import com.roselism.base.collect.ConverterList;
import com.roselism.base.collect.FilterList;
import com.roselism.base.util.convert.ApplicationInfo2AppInfo;
import com.roselism.base.util.convert.RunningAppProcessInfo2Process;
import com.roselism.base.util.function.Predicate;

import java.util.Collections;
import java.util.List;

/**
 * Created by simon on 16-5-23.
 */
public class PackageUtils {

    /**
     * 获取所有的安装过得app
     *
     * @param context 上下文对象
     * @return 返回安装的应用的集合
     */
    public static List<Application> getInstalledApp(Context context) {
        List<Application> applications = Lists.newArrayList(); // 获取安装过的集合
        PackageManager packageManager = context.getPackageManager(); // 获取包管理器
        List<ApplicationInfo> installedApps =
                packageManager.getInstalledApplications(PackageManager.GET_META_DATA); // 获取所有的安装过的应用
        ApplicationInfo2AppInfo converter = new ApplicationInfo2AppInfo(packageManager, context); // 创建类对象转换器
        for (ApplicationInfo app : installedApps) {
            applications.add(converter.convert(app)); // 转换
        }
        Collections.sort(applications); // 排序

        return applications; // 安装的app对象 简版
    }


    public static List<Process> getRunningProcess(Context context) {
        ActivityManager manager = getActivityManager(context);
        List<ActivityManager.RunningAppProcessInfo> processes = manager.getRunningAppProcesses();
        ConverterList<Process> converterList = new ConverterList<>(Lists.<Process>newArrayList());
        RunningAppProcessInfo2Process converter = new RunningAppProcessInfo2Process(context.getPackageManager(), context);
        converterList.addAll(processes, converter);
        return converterList;
    }

    /**
     * 根据包名获取这个包名对应的应用(application)的flag
     *
     * @return
     */
    public static int getFlag(Context context, final String packageName) {
        List<Application> applications = getInstalledApp(context);
        FilterList<Application> filterList = new FilterList<>(applications);
        int index = filterList.indexOf(new Predicate<Application>() {
            @Override
            public boolean test(Application appInfo) {
                return appInfo.getPackageName().equals(packageName);
            }
        });
        Application targetApplication = applications.get(index);
        return targetApplication.getFlag();
    }

    /**
     * 根据包名获取对应的application
     *
     * @param context
     * @param packageName
     * @return
     */
    public static Application getAppInfo(Context context, final String packageName) {
        List<Application> applications = getInstalledApp(context);
        FilterList<Application> filterList = new FilterList<>(applications);
        int index = filterList.indexOf(new Predicate<Application>() {
            @Override
            public boolean test(Application appInfo) {
                return appInfo.getPackageName().equals(packageName);
            }
        });
        Application targetApplication = applications.get(index);
        return targetApplication;
    }

    public static String availMem(Context context) {
//        List<Application> appInfos = getInstalledApp(context);
        ActivityManager activityManager = getActivityManager(context);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        String avail = android.text.format.Formatter.formatFileSize(context, memoryInfo.availMem);

        return avail;
    }

    public static long availMemLong(Context context) {
//        List<Application> appInfos = getInstalledApp(context);
        ActivityManager activityManager = getActivityManager(context);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        return memoryInfo.availMem;
    }

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
    public static void clean(Context context, String packageName) {
        ActivityManager activityManager = getActivityManager(context);
        activityManager.killBackgroundProcesses(packageName);
    }

    public interface OnCleanListener {
        void onCleanUp();
    }
}
