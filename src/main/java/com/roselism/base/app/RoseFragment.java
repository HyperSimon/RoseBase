package com.roselism.base.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 对Fragment的一些常用方法的简单封装
 *
 * @author: Hyper Simon Wang
 * @create_time: 2016/05/15 19:14
 * @packageName: com.roselism.mobilesafe24.fragment
 */
public abstract class RoseFragment extends Fragment {

    /**
     * 初始化view控件
     */
    protected abstract void initView();

    /**
     * 初始化Fragment当中的控件的事件
     */
    protected abstract void initEvent();

    /**
     * 初始化控件的数据,比如listview的显示
     * button 文字的显示
     * imageview图片的显示
     */
    protected abstract void initData();

    /**
     * 初始化与activity之间交互的listener，不要在这里放button等之类的点击监听事件
     * 哪些应该写在initEvent()
     */
    protected abstract void initListener();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
        initEvent();
        initData();
        initListener();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
