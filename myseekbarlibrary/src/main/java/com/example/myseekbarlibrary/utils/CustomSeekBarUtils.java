package com.example.myseekbarlibrary.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by ljc on 2017/4/2.
 */

public class CustomSeekBarUtils {
    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
