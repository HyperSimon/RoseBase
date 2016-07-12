package com.roselism.rosebase.app;

import android.support.annotation.LayoutRes;
import android.view.View;

import java.util.List;

public abstract class ViewHolder<E> implements View.OnClickListener {
    protected View mView;
    protected List<E> mdata;

    /**
     * @param data
     * @deprecated 不要使用, 因为没有复用ConvertView, 如果只有一种布局，推荐使用
     * <code>ViewHolder(View convertView, List<E> data)</code>, 如果有多种布局，
     * 推荐使用<code>ViewHolder(View convertview, @LayoutRes int key, List<E> data)</code>
     */
    protected ViewHolder(List<E> data) {
        mdata = data;
        mView = generateView();
        findView(mView);
    }

    protected ViewHolder(View convertView, List<E> data) {
        if (convertView != null) {
            mView = convertView;
            mView.setTag(convertView);
        } else mView = generateView();

        mView.setOnClickListener(this);
        findView(mView);
    }

    protected ViewHolder(View convertview, @LayoutRes int key, List<E> data) {
        if (convertview != null) {
            mView = convertview;
            mView.setTag(key, this);
        } else mView = generateView();

        mView.setOnClickListener(this);
        findView(mView);
        mdata = data;
    }

    protected abstract void findView(View view);

    protected abstract View generateView();

    public View getView() {
        return mView;
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 绑定数据
     */
    protected abstract void bindData(E data);
}
