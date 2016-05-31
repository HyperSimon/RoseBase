package com.roselism.base.content.pm;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.google.common.collect.Lists;
import com.roselism.base.collect.RoseList;
import com.roselism.base.util.convert.ApplicationInfo2AppInfo;
import com.roselism.base.util.function.Predicate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * Created by simon on 16-5-23.
 */
public class PackageManagers {

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

    /**
     * 根据包名获取这个包名对应的应用(application)的flag
     *
     * @return
     */
    public static int getFlag(Context context, final String packageName) {
        List<Application> applications = getInstalledApp(context);
        RoseList<Application> filterList = new RoseList<>(applications);
        int index = filterList.indexOf(new Predicate<Application>() {
            @Override
            public boolean test(Application appInfo) {
                return appInfo.getPackageName().equals(packageName);
            }
        });
        Application targetApplication = applications.get(index);
        return targetApplication.getFlag();
    }

    public static Drawable getDrawable(Context context, final String packageName) {
        List<Application> applications = getInstalledApp(context);
        RoseList<Application> filterList = new RoseList<>(applications);
        int index = filterList.indexOf(new Predicate<Application>() {
            @Override
            public boolean test(Application appInfo) {
                return appInfo.getPackageName().equals(packageName);
            }
        });
        Application targetApplication = applications.get(index);
        return targetApplication.getIcon();
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
        RoseList<Application> filterList = new RoseList<>(applications);
        int index = filterList.indexOf(new Predicate<Application>() {
            @Override
            public boolean test(Application appInfo) {
                return appInfo.getPackageName().equals(packageName);
            }
        });
        return applications.get(index);
    }


    /**
     * 需要
     * <uses-permission android:name="android.permission.GET_PACKAGE_SIZE"></uses-permission>
     * 这个权限
     *
     * @param pm
     * @param packageName
     * @param observer
     */
    public static void getPackageSizeInfo(PackageManager pm, String packageName, IPackageStatsObserver observer) {
        Class clazz = pm.getClass();
        try {
            Method method = clazz.getDeclaredMethod("getPackageSizeInfo", String.class, IPackageStatsObserver.class);
            method.invoke(pm, packageName, observer); // 请求
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void freeStorageAndNotify(PackageManager packageManager, int maxValue, IPackageDataObserver.Stub observer) {
        Class clazz = packageManager.getClass();
        try {
            Method method = clazz.getDeclaredMethod("freeStorageAndNotify", long.class, IPackageDataObserver.class);
            method.invoke(packageManager, maxValue, observer); // 请求
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
