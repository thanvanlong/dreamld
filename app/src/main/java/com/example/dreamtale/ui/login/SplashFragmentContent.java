package com.example.dreamtale.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.dto.ContentSplash;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SplashFragmentContent extends BaseFragment<LoginActivityPresenter, SplashActivity> {

    @BindView(R.id.rcy_splash)
    protected ViewPager2 rcySplash;
    List<ContentSplash> list;
    SplashAdapter splashAdapter;
    private static SplashFragmentContent mInstance;

    public static SplashFragmentContent getmInstance() {
        return mInstance;
    }

    public static void setmInstance(SplashFragmentContent mInstance) {
        SplashFragmentContent.mInstance = mInstance;
    }

    public ViewPager2 getRcySplash() {
        return rcySplash;
    }

    public void setRcySplash(ViewPager2 rcySplash) {
        this.rcySplash = rcySplash;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInstance = this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_splash_content;
    }


    @Override
    public void onPrepareLayout() {
        list = new ArrayList<>();
        list.add(new ContentSplash());
        list.add(new ContentSplash());
        list.add(new ContentSplash());
        splashAdapter = new SplashAdapter(list);
        rcySplash.setAdapter(splashAdapter);
        setmInstance(this);
    }

    @Override
    public LoginActivityPresenter onCreatePresenter() {
        return null;
    }
}
