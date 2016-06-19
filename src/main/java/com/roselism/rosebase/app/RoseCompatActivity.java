package com.roselism.rosebase.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

/**
 * 使用须知：
 * 在子类中需要将
 * setContentView(R.layout.activity_contacts);
 * super.onCreate(savedInstanceState);
 * 更换位置，正确位置如上代码所示
 * <p/>
 * 默认调用
 * initView();
 * initEvent();
 * initData();
 * 这三个方法，实现类只需要实现这三个抽象方法就可以了
 * 注意，父类中方法的调用是固定的，所以如果有特定调用需求，请重写父类的onCreate(@Nullable Bundle savedInstanceState) 方法
 *
 * @author: Hyper Simon Wang
 * @create_time: 2016/05/12 11:27
 * @packageName: com.roselism.mobilesafe24.activity
 */
public abstract class RoseCompatActivity extends AppCompatActivity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        initData();
        initEvent();
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化事件
     */
    protected abstract void initEvent();


    /**
     * 获取当前上下文对象
     *
     * @return
     */
    protected abstract Context getContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
