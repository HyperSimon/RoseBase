package com.roselism.base.app;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * 基于 BaseAdapter 的抽象适配器
 * Created by simon on 16-5-20.
 */
public abstract class RoseAdapter2<VH extends RoseAdapter2.ViewHolder, E> extends BaseAdapter {

    protected Context context;
    protected List<E> mData;
    protected View mConvertView;
    protected @LayoutRes int layout;


    public RoseAdapter2(Context context, List<E> mData, @LayoutRes int layout) {
        this.context = context;
        this.mData = mData;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
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
        VH viewholder = mConvertView == null ? createViewHolder() : (VH) mConvertView.getTag();

        return viewholder;
    }

    /**
     * 创建一个viewholder
     *
     * @return
     */
    @NonNull
    protected abstract VH createViewHolder();


    public abstract static class ViewHolder {
        public ViewHolder(View viewItem) {

        }
    }
}
