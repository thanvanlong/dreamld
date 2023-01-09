package com.example.dreamtale.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseActivity;
import com.example.dreamtale.network.dto.DeviceInfo;
import com.example.dreamtale.ui.home.HomeActivity;
import com.example.dreamtale.utils.PrefManager;


public class SplashActivity extends BaseActivity<LoginPresenter> {
    boolean isFromNotify = false;

    public boolean isFromNotify() {
        return isFromNotify;
    }

    public void setFromNotify(boolean fromNotify) {
        isFromNotify = fromNotify;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void onPrepareLayout() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if ( bundle != null && bundle.getString("notification") != null) {
            isFromNotify = true;
        }

       if (PrefManager.isFirstStart(getViewContext()) || !PrefManager.isLogged(getViewContext())) {
           final Handler handler = new Handler();
           handler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   PrefManager.setIsFirstStart(getViewContext(), false);
                   startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
                   SplashActivity.this.finish();
               }
           }, 3000);
       } else if (PrefManager.isLogged(getViewContext())) {
           // doautologin
           final Handler handler = new Handler();
           handler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   gotoHome();
               }
           }, 3000);

       }
    }

    public void doAutoLogin(DeviceInfo deviceInfo) {
        //TODO do auto login
    }

    public void gotoHome() {
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("from_notification", isFromNotify);
        startActivity(intent);
        SplashActivity.this.finish();
//        getViewContext().getSupportFragmentManager().popBackStack();
    }


    @Override
    public LoginPresenter onCreatePresenter() {
        return null;
    }
}
