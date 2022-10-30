package com.example.dreamtale.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;

public class CompatibilityUtils {

    public static final float MARGIN_PERCENT = 0.03f;
    public static final float SPACING_PERCENT = 0.04f;
    public static final float NUMBER_STORY_MOBILE = 2.3f;
    public static final float NUMBER_LISTENING_MOBILE = 1.2f;
    public static final float NUMBER_CONTENT_MOBILE = 2.5f;


    public static boolean isTablet(Context context) {
        if (context == null) {
            return  false;
        }
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    public static int getScreenMargin(Context context) {
        return (int) (DeviceUtils.getDeviceSizePortrait((Activity) context).x * MARGIN_PERCENT);
    }


    public static int getItemSpacing(Context context) {
        return (int) (DeviceUtils.getDeviceSizePortrait((Activity) context).x * SPACING_PERCENT);
    }

    public static int getWidthListeningItem(Context context) {
        int screenWidth = DeviceUtils.getDeviceSizePortrait((Activity) context).x;
        float margin = getScreenMargin(context);
        float spacing = getItemSpacing(context) * 0;
        int width = (int) ((screenWidth - spacing - margin) / NUMBER_LISTENING_MOBILE);
        return width;
    }


    public static int getWidthContentItem(Context context) {
        int screenWidth = DeviceUtils.getDeviceSizePortrait((Activity) context).x;
        float margin = getScreenMargin(context);
        float spacing = getItemSpacing(context) * 0;
        int width = (int) ((screenWidth - spacing - margin) / NUMBER_CONTENT_MOBILE);
        return width;
    }
}
