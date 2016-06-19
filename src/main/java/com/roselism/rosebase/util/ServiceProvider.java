package com.roselism.rosebase.util;

import android.content.Context;

/**
 * @author: Hyper Simon Wang
 * @create_time: 2016/05/16 08:49
 * @packageName: com.roselism.mobilesafe24.util
 * @deprecated 无卵用，将在下下个版本(4)删除
 */
public class ServiceProvider<T> {
    public void getService(Context context, String serviceName) {
        context.getSystemService(serviceName);
    }
}
