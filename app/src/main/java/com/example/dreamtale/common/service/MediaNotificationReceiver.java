package com.example.dreamtale.common.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MediaNotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int action = intent.getIntExtra("action_media", 0);
        Log.e("longtv", "onReceive: " );
        Intent intentService = new Intent(context, MediaNotificationService.class);
        intentService.putExtra("longtv", action);
        context.startService(intentService);
    }
}
