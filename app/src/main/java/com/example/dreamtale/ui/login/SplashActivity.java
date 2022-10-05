package com.example.dreamtale.ui.login;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseActivity;
import com.example.dreamtale.base.BasePresenter;
import com.example.dreamtale.utils.PrefManager;

import butterknife.BindView;


public class SplashActivity extends BaseActivity<LoginPresenter> {
    private boolean mIsInitialed = false;
    private static SplashActivity mInstance;

    @BindView(R.id.layout_container)
    LinearLayout linearLayout;
    public static SplashActivity getmInstance() {
        return mInstance;
    }

    public static void setmInstance(SplashActivity mInstance) {
        SplashActivity.mInstance = mInstance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onPrepareLayout() {
        if (!mIsInitialed) {
            SplashFragmentContent splashFragmentContent = new SplashFragmentContent();
            SplashFragmentHeader splashFragmentHeader = new SplashFragmentHeader(R.string.app_name);
            SplashFragmentFooter splashFragmentFooter = new SplashFragmentFooter();
            addFragment(R.id.frg_splash_content, splashFragmentContent, null, false, SplashFragmentContent.class.getSimpleName());
            addFragment(R.id.frg_splash_header, splashFragmentHeader, null,  false, SplashFragmentHeader.class.getSimpleName());
            addFragment(R.id.frg_splash_footer, splashFragmentFooter, null, false, SplashFragmentFooter.class.getSimpleName());
            mIsInitialed = true;
            setmInstance(this);
        }

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideSoftKeyboard();
                return false;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    public BaseActivity getViewContext() {
        return this.getViewContext();
    }
}
