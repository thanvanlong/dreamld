package com.example.dreamtale.ui.login;

import androidx.viewpager2.widget.ViewPager2;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseActivity;
import com.example.dreamtale.base.BasePresenter;
import com.example.dreamtale.dto.ContentSplash;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator2;
import me.relex.circleindicator.CircleIndicator3;

public class SplashActivity extends BaseActivity<LoginActivityPresenter> {
    @BindView(R.id.rcy_splash)
    protected ViewPager2 rcySplash;
    @BindView(R.id.indicator)
    protected CircleIndicator3 indicator;

    private SplashAdapter splashAdapter;
    private  List<ContentSplash> splashList;


    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void onPrepareLayout() {
        splashList = new ArrayList<>();
        splashList.add(new ContentSplash());
        splashList.add(new ContentSplash());
        splashList.add(new ContentSplash());
        splashAdapter = new SplashAdapter(splashList);
        rcySplash.setAdapter(splashAdapter);
        indicator.setViewPager(rcySplash);
    }

    @Override
    public BasePresenter onCreatePresenter() {
        return null;
    }
}
