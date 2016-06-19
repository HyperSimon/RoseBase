package com.roselism.rosebase.widget;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 走马灯textview
 *
 * @author: Hyper Simon Wang
 * @create_time: 2016/05/12 21:58
 * @packageName: com.roselism.mobilesafe24.library.view
 */
public class MarqueeTextview extends TextView {
    public MarqueeTextview(Context context) {
        super(context);
        initView();
    }

    public MarqueeTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MarqueeTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    void initView() {
        setSingleLine();
        setLines(1); // 设置只有一行
        setFocusable(true);
        setFocusableInTouchMode(true);
        setEllipsize(TextUtils.TruncateAt.MARQUEE); // 走马灯
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (hasWindowFocus)
            super.onWindowFocusChanged(true);
    }

    /**
     * 是否获取了焦点
     * 跑马灯的效果只有在获取了焦点的情况下才会运行，所以强制让他获取焦点不管实际情况如何
     *
     * @return
     */
    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused)
            super.onFocusChanged(true, direction, previouslyFocusedRect);
    }
}
