package com.roselism.base.app;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.google.common.collect.Lists;
import com.roselism.base.collect.RoseList;
import com.roselism.base.util.convert.Converter;

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

        RoseList<Process> converterList = new RoseList<>(Lists.<Process>newArrayList());

        RunningAppProcessInfo2Process converter = new RunningAppProcessInfo2Process(context.getPackageManager(), context);
        converterList.addAll(processes, converter);
        return converterList;
    }

    public static String availMem(Context context) {


        return android.text.format.Formatter.formatFileSize(context, availMemLong(context));
    }

    public static long availMemLong(Context context) {
        ActivityManager activityManager = getActivityManager(context);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        return memoryInfo.availMem;
    }


    /**
     * 进程 domain
     *
     * @author: Hyper Simon Wang
     * @create_time: 2016/05/23 09:16
     * @packageName: com.roselism.mobilesafe24.model.domain
     */
    public static final class Process implements Comparable<Process> {

        public static final int FLAG_HEAD_ITEM = 0x16; // 头部标记位

        private Converter<Process, ?> converter; // 转换器
        private Drawable icon; // app icon
        private String name; // 进程的名字
        private long size; // 大小
        private boolean selected; // 是否被选中

        /**
         * 当前process类型的标志
         * 因为flag和包名是一一对应的，所以可以将包中的flag属性直接赋值给process
         * {@link com.roselism.base.util.convert.RunningAppProcessInfo2Process}
         */
        private int flag;


        public <R> void setConverter(Converter<Process, R> converter) {
            this.converter = converter;
        }

        public <R> R convert() {
            return (R) this.converter.convert(this);
        }


        public Drawable getIcon() {
            return icon;
        }

        public void setIcon(Drawable icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        /**
         * 实现比较接口
         * 用于集合中的排序
         *
         * @param second
         * @return
         */
        @Override
        public int compareTo(@NonNull Process second) {
            // 当前是User process   se是System process --> -1 排在前面

            // 当前User process     se是User process --> 字典顺序

            // 当前是System process  se是User process --> -1

            // 当前是System process se是System process --> 字典顺序

            if (!isSystemProcess(this) && isSystemProcess(second))
                return 1;
            if (!isSystemProcess(this) && !isSystemProcess(second))
                return this.getName().compareTo(second.getName());
            if (isSystemProcess(this) && !isSystemProcess(second))
                return -1;
            if (isSystemProcess(this) && isSystemProcess(second))
                return this.getName().compareTo(second.getName());


            // TODO: 16-5-25 可以使用方法引用替换
            return 1;
        }

        /**
         * 一个进程是否是系统进程
         *
         * @param process
         * @return
         */
        public boolean isSystemProcess(Process process) {
            return (process.getFlag() & ApplicationInfo.FLAG_SYSTEM) == 0;
        }


        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        @Override
        public String toString() {
            return "Process{" +
                    "selected=" + selected +
                    ", size=" + size +
                    ", name='" + name + '\'' +
                    ", icon=" + icon +
                    '}';
        }
    }
}
