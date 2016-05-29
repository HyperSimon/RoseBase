package com.roselism.base;

import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

import com.roselism.base.util.convert.Converter;

/**
 * 进程 domain
 *
 * @author: Hyper Simon Wang
 * @create_time: 2016/05/23 09:16
 * @packageName: com.roselism.mobilesafe24.model.domain
 */
public class Process implements Comparable<Process> {

    public static final int FLAG_HEAD_ITEM = 0x16; // 头部标记位
//    public static final int FLAG_NORMAL_ITEM = 0x17; // 头部标记位

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
    public int compareTo(Process second) {
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

    /**
     * 要转换成别人的对象
     */
    private class WhatTF implements Converter {

        @Override
        public Object convert(Object parameter) {
            return null;
        }
    }
}
