package com.example.dreamtale.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;

import com.example.dreamtale.network.dto.DeviceInfo;

public class DeviceUtils {

    public static DeviceInfo getDeviceInfo (Context context) {
        String deviceName = android.os.Build.MODEL;
        String osVersion = String.valueOf(Build.VERSION.SDK_INT);
        String deviceType = (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE
                ? "Tablet" : "Phone";
        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return new DeviceInfo(deviceId, deviceType, osVersion, deviceName);
    }

    public static Point getDeviceSizePortrait(Activity context) {
        if (context==null) return null;
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int x = Math.min(size.x, size.y);
        int y = Math.max(size.x, size.y);
        return new Point(x, y);
    }


    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
