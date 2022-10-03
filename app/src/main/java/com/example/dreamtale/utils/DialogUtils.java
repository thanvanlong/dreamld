package com.example.dreamtale.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.example.dreamtale.R;

import java.util.logging.Logger;

public class DialogUtils {

    private static AlertDialog sAlert;
    private static ProgressDialog sProgress;

    public static synchronized void showProgressDialog(final Activity activity) {
        try {
            if (activity != null && !activity.isFinishing()) {
                if (sProgress != null && sProgress.isShowing()) {
                    return;
                }
                sProgress = new ProgressDialog(activity);
                sProgress.setCancelable(false);
                sProgress.setIndeterminateDrawable(activity.getResources().getDrawable(R.drawable.bg_loading));
                sProgress.show();
                sProgress.setMessage(activity.getString(R.string.loading));
            }

        } catch (Exception e) {

        }
    }

    public static void dismissProgressDialog(final Activity activity) {
        try {
            if (activity != null && !activity.isFinishing()) {
                if (sProgress != null && sProgress.isShowing()) {
                    sProgress.dismiss();
                }
            }
        } catch (Exception e) {

        }
    }

    public static void showToastMessage(String message, Context context, boolean center) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        if (center) {
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.show();
    }
}
