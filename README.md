# Rosebase Android快速开发包

## app

### RoseAdapter2
对于现有的Adapter的一些封装
将原来创建一个Adapter的步骤从5布缩减到3步

#### simple

对比
Adapter的当前写法

```java
public class BlackListAdapter extends BaseAdapter {
    Context mContext;
    List<BlackNumber> mList;

    EmptyDataListener mEmptyDataListener;

    // TODO: 2016/5/19 添加黑名单修改的功能
    public BlackListAdapter(Context mContext, List<BlackNumber> mList, EmptyDataListener listener) {
        // your code..
    }

    /**
     * 向adapter中添加数据
     *
     * @param blackNumber 需要添加的黑名单对象
     */
    public void addData(BlackNumber blackNumber) {
        // your code..
    }

    public void setmEmptyDataListener(EmptyDataListener listener) {
        // your code..
    }

    @Override
    public int getCount() {
        // your code..
    }

    @Override
    public Object getItem(int position) {
        // your code..
    }

    @Override
    public long getItemId(int position) {
        // your code..
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // your long code..
    }


    static class ViewHolder {
        @BindView(R.id.tv_type) TextView mTvType;
        @BindView(R.id.tv_phoneNumber) TextView mTvPhoneNumber;
        @BindView(R.id.iv_delete) ImageView mIvDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

```



使用 RoseAdapter


```java
public class DataCountAdapter extends RoseAdapter<DataCountAdapter.DataViewHolder, Application> {

    public DataCountAdapter(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    protected void bindData(int position, DataViewHolder viewholder) {
        Application application = mData.get(position);
        viewholder.mIvIcon.setImageDrawable(application.getIcon());
        viewholder.mTvTitle.setText(application.getAppName());
        viewholder.mTvSubtitle.setText(application.getPackageName());

        long mobileRxBytes = TrafficStats.getMobileRxBytes();
        long mobileTxBytes = TrafficStats.getMobileTxBytes();

        viewholder.mProgressReceiver.setMax((int) mobileRxBytes);
        viewholder.mProgressTransfer.setMax((int) mobileTxBytes);

        viewholder.mProgressReceiver.setProgress((int) application.getRxBytes());
        viewholder.mProgressTransfer.setProgress((int) application.getTxBytes());
    }

    @NonNull
    @Override
    protected DataViewHolder createViewHolder(View convertView) {
        mConvertView = View.inflate(context, R.layout.list_item_data, null);
        DataViewHolder viewHolder = new DataViewHolder(mConvertView);
        mConvertView.setTag(viewHolder);
        return viewHolder;
    }

    static class DataViewHolder extends RoseAdapter.ViewHolder {
        @BindView(R.id.iv_icon) ImageView mIvIcon;
        @BindView(R.id.tv_title) TextView mTvTitle;
        @BindView(R.id.tv_subtitle) TextView mTvSubtitle;
        @BindView(R.id.layout_info) RelativeLayout mLayoutInfo;
        @BindView(R.id.progress_receiver) ProgressBar mProgressReceiver;
        @BindView(R.id.progress_transfer) ProgressBar mProgressTransfer;

        DataViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

```

只需要实现两个方法和一个子类就能完成一个Adapter
分别是数据和ViewHolder之间的绑定，和确定如何创建一个ViewHolder
下面的ViewHolder中用到了ButterKnife的以来注入，其效果等同于findViewById,这里不再赘述

RoseAdapter<DataCountAdapter.DataViewHolder, Application>这两个的泛性类型分别代表着
具体Adapter类型（也就是你自己实现的ViewHolder）和数据集中（List）的类型





