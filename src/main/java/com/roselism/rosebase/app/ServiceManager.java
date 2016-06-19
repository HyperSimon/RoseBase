package com.roselism.rosebase.app;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import java.util.List;

/**
 * @author: Hyper Simon Wang
 * @create_time: 2016/05/18 23:22
 * @packageName: com.roselism.base.app
 */
public class ServiceManager {

    private static final String TAG = "ServiceManager";

    /**
     * @param context
     * @param componentName
     * @return
     * @see @link{isRunning(Context context, ComponentName componentName, int limited)}
     */
    public static boolean isRunning(Context context, ComponentName componentName) {
        return isRunning(context, componentName, 100);
    }

    /**
     * 查看某个服务是否已经开启
     *
     * @param context       上下文对象
     * @param componentName
     * @param limited       要查询的services的数量
     * @return 如果componentName所代表的服务当前正在运行，那么则返回true，反之则返回false
     */
    public static boolean isRunning(Context context, ComponentName componentName, int limited) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE); // 获取Activity服务
        List<ActivityManager.RunningServiceInfo> runningServices = manager.getRunningServices(limited); // 获取正在运行的服务列表
        for (ActivityManager.RunningServiceInfo service : runningServices) {
            if (componentName.getClassName().equals(service.service.getClassName())) {
                // 已经开启了服务
//                Log.i(TAG, "isRunning: service " + service.service.getClassName() + " ----------------------is running --------");
                return true;
            }
        }
        return false;
    }
}
