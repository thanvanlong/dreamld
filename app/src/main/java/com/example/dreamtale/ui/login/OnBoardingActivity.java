package com.example.dreamtale.ui.login;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseActivity;
import com.example.dreamtale.network.dto.ContentSplash;
import com.example.dreamtale.network.dto.DeviceInfo;
import com.example.dreamtale.utils.PrefManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator3;


public class OnBoardingActivity extends BaseActivity<LoginPresenter> {
    public static final int ACTIVITY_LOGIN = 1000;
    public static final int ACTIVITY_SIGNUP = 1001;
    private boolean mIsInitialed = false;
    private static OnBoardingActivity mInstance;

    @BindView(R.id.rcy_splash)
    protected ViewPager2 mViewPager2;
    @BindView(R.id.indicator)
    protected CircleIndicator3 mIndicator;
    @BindView(R.id.container_onboarding)
    protected ConstraintLayout mContainerOnBoarding;
    @BindView(R.id.btn_login)
    protected Button btnLogin;
    @BindView(R.id.btn_signup)
    protected Button btnSignup;
    @BindView(R.id.screen_onboarding)
    protected RelativeLayout mScreenBoarding;
    private SplashAdapter splashAdapter;

    public static OnBoardingActivity getmInstance() {
        return mInstance;
    }

    public static void setmInstance(OnBoardingActivity mInstance) {
        OnBoardingActivity.mInstance = mInstance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_onboarding;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onPrepareLayout() {
        List<ContentSplash> splashList = new ArrayList<>();
        splashList.add(new ContentSplash());
        splashList.add(new ContentSplash());
        splashList.add(new ContentSplash());

        splashAdapter = new SplashAdapter(splashList);
        mViewPager2.setAdapter(splashAdapter);
        mIndicator.setViewPager(mViewPager2);

        setListener();

    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return null;
    }


    private void setListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnBoardingActivity.this, LoginActivity.class);
                intent.putExtra("type_screen", ACTIVITY_LOGIN);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnBoardingActivity.this, LoginActivity.class);
                intent.putExtra("type_screen", ACTIVITY_SIGNUP);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


}
