package com.roselism.rosebase.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.common.collect.Lists;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 基于 BaseAdapter 的抽象适配器
 * Created by simon on 16-5-20.
 */
public abstract class RoseAdapter<VH extends RoseAdapter.RoseViewHolder, E> extends BaseAdapter {

    protected Context context;
    protected List<E> mData;

    public RoseAdapter(@NonNull Context context, @Nullable List<E> data) {
        checkNotNull(context);
        this.context = context;
        this.mData = data == null ? Lists.<E>newArrayList() : data;
    }

    @Override
    public int getCount() {
        if (mData != null) return mData.size();
        return 0;
    }

    public void setData(@NonNull List<E> data) {
        checkNotNull(data);
        mData = data;
        notifyDataSetChanged();
    }

    public void addData(@NonNull E e) {
        checkNotNull(e);
        mData.add(e);
        notifyDataSetChanged();
    }

    public void addData(@NonNull List<E> data) {
        checkNotNull(data);
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @NonNull
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

        VH viewholder = getViewHolder(convertView);
        bindData(position, viewholder); // 为viewholder 绑定数据

        return viewholder.mConvertView;
    }

    protected abstract void bindData(int position, VH viewholder);

    protected VH getViewHolder(View convertView) {
        return convertView == null ? createViewHolder() : (VH) convertView.getTag();
    }

    protected abstract VH createViewHolder();

    public abstract static class RoseViewHolder {
        View mConvertView;

        public RoseViewHolder(View convertView) {
            this.mConvertView = convertView;
            mConvertView.setTag(this);
        }
    }
}
