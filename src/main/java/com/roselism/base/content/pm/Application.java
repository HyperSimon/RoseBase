package com.roselism.base.content.pm;

import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.roselism.base.util.convert.Converter;

/**
 * @author: Hyper Simon Wang
 * @create_time: 2016/05/23 09:16
 * @packageName: com.roselism.mobilesafe24.model.domain
 */
public class Application implements Comparable<Application> {

    public static final int FLAG_HEAD = 0x16;
    public static final int FLAG_IS_VIRUS = 1 << 30;
    private long rxBytes; // 接收到的字节
    private long txBytes; // 发送的字节
    private transient Converter<Application, ?> converter; // 转换器
    private Drawable icon; // app icon
    private String appName; // app name
    private String packageName; // 应用包名
    private String size; // 应用大小
    private int flag;
    private int uid;

    private Application() {
    }

    public static Application createApplication() {
        return new Application();
    }

    public long getRxBytes() {
        return rxBytes;
    }

    public void setRxBytes(long rxBytes) {
        this.rxBytes = rxBytes;
    }

    public long getTxBytes() {
        return txBytes;
    }

    public void setTxBytes(long txBytes) {
        this.txBytes = txBytes;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag |= flag;
    }

    public <R> void setConverter(Converter<Application, R> converter) {
        this.converter = converter;
    }

    public <R> R convert() {
        return (R) this.converter.convert(this);
    }

    private boolean isSystemApp(int flag) {
        return (flag & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM;
    }

    /**
     * 实现比较接口
     * 用于集合中的排序
     *
     * @param second
     * @return
     */
    @Override
    public int compareTo(@NonNull Application second) {
        // 都是系统应用 --> 按照系统应用的字典顺序

        // 第一个是系统应用 第二个是用户应用 --> 用户应用排在前面 系统应用排在后面 返回 1

        // 第一个是用户应用 第二个是系统应用 --> -1

        // 都是用户应用 --> 排名字

        if (isSystemApp(this.getFlag()) && isSystemApp(second.getFlag())) // 第一个是系统应用
            return this.getAppName().compareTo(second.getAppName());
        else if (isSystemApp(this.getFlag()) && !isSystemApp(second.getFlag()))  // 第一个不是系统应用 第二个是系统应用
            return 1;
        else if (!isSystemApp(this.getFlag()) && isSystemApp(second.getFlag()))
            return -1;

        // 使用方法引用将更为简介
        return this.getAppName().compareTo(second.getAppName());
    }

    @Override
    public String toString() {
        return "Application{" +
                "flag=" + flag +
                ", size='" + size + '\'' +
                ", packageName='" + packageName + '\'' +
                ", appName='" + appName + '\'' +
                '}';
    }
}
