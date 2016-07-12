package com.roselism.rosebase.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractAdapter<E> extends BaseAdapter {

    protected Context mContext;
    protected List<E> mDatas;

    public AbstractAdapter(Context context, List<E> datas) {
        mContext = context;
        mDatas = datas;
    }

    public void setData(@NonNull List<E> data) {
        checkNotNull(data);
        mDatas = data;
        notifyDataSetChanged();
    }

    public void addData(@NonNull E e) {
        checkNotNull(e);
        mDatas.add(e);
        notifyDataSetChanged();
    }

    public void addData(@NonNull List<E> data) {
        checkNotNull(data);
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
