package com.roselism.rosebase.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 基于 BaseAdapter 的抽象适配器
 * Created by simon on 16-5-20.
 */
public abstract class RoseAdapter2<VH extends RoseAdapter2.RoseViewHolder, E> extends BaseAdapter {

    protected Context context;
    protected List<E> mData;
    protected View mConvertView;

    public RoseAdapter2(Context context, List<E> data) {
        this.context = context;
        this.mData = data;
    }


    @Override
    public int getCount() {
        if (mData != null) return mData.size();
        return 0;
    }

    public void setData(List<E> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void addData(E e) {
        mData.add(e);
        notifyDataSetChanged();
    }

    public void addData(List<E> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public E getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        this.mConvertView = convertView;
        VH viewholder = getViewHolder();
        bindData(position, viewholder); // 为viewholder 绑定数据

        return mConvertView;
    }

    protected abstract void bindData(int position, VH viewholder);

    protected VH getViewHolder() {
        return mConvertView == null ? createViewHolder() : (VH) mConvertView.getTag();
    }

    /**
     * 创建一个viewholder
     *
     * @return
     */
    @NonNull
    protected abstract VH createViewHolder();


    public abstract static class RoseViewHolder {
        public RoseViewHolder(View viewItem) {

        }
    }
}
