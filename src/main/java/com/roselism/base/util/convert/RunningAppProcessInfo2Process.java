package com.roselism.base.util.convert;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;

import com.roselism.base.Process;
import com.roselism.base.util.PackageUtils;

/**
 * RunningAppProcessInfo 转换成 Process
 * <p>
 * Created by simon on 16-5-25.
 */
public class RunningAppProcessInfo2Process implements Converter<ActivityManager.RunningAppProcessInfo, Process> {

    private PackageManager packageManager;
    private Context context;

    public RunningAppProcessInfo2Process(PackageManager packageManager, Context context) {
        this.packageManager = packageManager;
        this.context = context;
    }


    @Override
    public Process convert(ActivityManager.RunningAppProcessInfo processInfo) {
        // 可以通过processInfo 直接获取进程的名字，进程号之类的基本信息

        Process process = new Process();

        process.setName(processInfo.processName); // 设置进程的名字
        String packageName = packageManager.getPackagesForUid(processInfo.uid)[0];

        process.setFlag(PackageUtils.getFlag(context, packageName)); // 将这个应用的所属的flag附给这个进程
        process.setIcon(PackageUtils.getAppInfo(context, packageName).getIcon());
        return process;
    }
}
