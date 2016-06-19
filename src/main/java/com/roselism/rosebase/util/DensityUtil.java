package com.roselism.rosebase.util;

import android.content.Context;

/**
 * Created by simon on 16-6-10.
 */
public class DensityUtil {

    //    px = dp * (dpi / 160)
    //    density  = dpi / 160
    public static int dp2Px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        int px = (int) (dp * density);
        return px;
    }
}
