package com.roselism.rosebase.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.roselism.rosebase.R;

/**
 * 设置项View
 * 根据所处的位置的不同，设置不同的背景
 *
 * @author: Hyper Simon Wang
 * @create_time: 2016/05/13 18:25
 * @packageName: com.roselism.mobilesafe24.library.view
 */
public abstract class SettingItemView extends RelativeLayout {

    public static final int POSITION_TOP = 1; // 位于顶部
    public static final int POSITION_MIDDLE = 2; // 位于中间
    public static final int POSITION_BOTTOM = 3; // 位于底部
    public static final int POSITION_SINGLE = 4; // 只有一个的选项
    private static final String TAG = "SettingItemView";

    public TextView mTvText;
    public ImageView mIvCheckBox;
    public RelativeLayout mLayoutItem;

    private View settingItem; // 这个设置项

    public SettingItemView(Context context) {
        this(context, null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
        initEvent();
    }

    /**
     * 初始化控件
     *
     * @param context
     * @param attrs
     */
    private void initView(Context context, AttributeSet attrs) {
        settingItem = View.inflate(context, R.layout.view_setting_item, this);
//        ButterKnife.bind(settingItem);
        settingItem.setClickable(true);

        // 获取属性列表
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);

        // 设置背景
        int positon = typedArray.getInt(R.styleable.SettingItemView_item_position, 2); // 当前控件所处的位置信息
        setBackground(positon); // 设置背景

        // 设置选中框
        boolean hasCheckBox = typedArray.getBoolean(R.styleable.SettingItemView_item_checkbox, false);
        if (hasCheckBox)// 如果有选中框 则进行设置
            mIvCheckBox.setImageResource(R.mipmap.ic_select_on);
        else mIvCheckBox.setVisibility(GONE);

        // 设置setting内容
        String content = typedArray.getString(R.styleable.SettingItemView_item_content);
        mTvText.setText(content);

        typedArray.recycle();
    }

    /**
     * 根据控件所处的位置设置控件的背景
     *
     * @param positon 所处的位置
     */
    private void setBackground(int positon) {
        switch (positon) {
            case POSITION_TOP: // 头
                settingItem.setBackgroundResource(R.drawable.selector_setting_item_top);
                break;

            case POSITION_MIDDLE: // 中间
                settingItem.setBackgroundResource(R.drawable.selector_setting_item_middle);
                break;

            case POSITION_BOTTOM: // 尾部
                settingItem.setBackgroundResource(R.drawable.selector_setting_item_bottom);
                break;

            case POSITION_SINGLE: // 单例
                settingItem.setBackgroundResource(R.drawable.selector_setting_item_single);
                break;
        }
    }

    /**
     * 初始化事件
     */
    void initEvent() {
    }

    /**
     * 获取是否是自动更新
     *
     * @param context
     * @return
     */
    public abstract boolean isChecked(Context context);


    /**
     * 设置选中框的选中状态
     *
     * @param checked
     */
    public abstract void setChecked(Context context, String key, boolean checked);

    /**
     * 反转状态框的状态并更换图标
     *
     * @param context
     */
    public abstract void reverseChecked(Context context, String key);
}
