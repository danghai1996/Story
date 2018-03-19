package com.example.nhem.story.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;


public class Utils {
    public static int getActivityWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getActivityHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }
}
