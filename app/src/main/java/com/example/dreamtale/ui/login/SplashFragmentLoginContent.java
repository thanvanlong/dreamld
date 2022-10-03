package com.example.dreamtale.ui.login;

import android.widget.Button;
import android.widget.LinearLayout;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;

import butterknife.BindView;

public class SplashFragmentLoginContent extends BaseFragment<LoginPresenter, SplashActivity> {

    @BindView(R.id.btn_login_fb)
    protected Button btnLoginFb;
    @BindView(R.id.btn_login_gg)
    protected Button btnLoginGg;
    @BindView(R.id.container_edt_phone)
    protected LinearLayout containerEdtPhone;
    @BindView(R.id.container_edt_pass)
    protected LinearLayout containerEdtPass;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_splash_login;
    }

    @Override
    public void onPrepareLayout() {

    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return null;
    }
}
