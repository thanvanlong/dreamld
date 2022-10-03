package com.example.dreamtale.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

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

}
