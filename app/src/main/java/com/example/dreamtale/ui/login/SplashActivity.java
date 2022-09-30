package com.example.dreamtale.ui.login;


import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseActivity;
import com.example.dreamtale.base.BasePresenter;


public class SplashActivity extends BaseActivity<LoginActivityPresenter> {
    private boolean mIsInitialed = false;
    private static SplashActivity mInstance;

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

    @Override
    public void onPrepareLayout() {
        if (!mIsInitialed) {
            SplashFragmentContent splashFragmentContent = new SplashFragmentContent();
            SplashFragmentHeader splashFragmentHeader = new SplashFragmentHeader(R.string.app_name);
            SplashFragmentFooter splashFragmentFooter = new SplashFragmentFooter();
            addFragment(R.id.frg_splash_content, splashFragmentContent, null, false, "CONTENT_VIEWPAGER");
            addFragment(R.id.frg_splash_header, splashFragmentHeader, null,  false, "Header");
            addFragment(R.id.frg_splash_footer, splashFragmentFooter, null, false, "Footer");
            mIsInitialed = true;
            setmInstance(this);
        }
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
