package com.example.dreamtale.ui.login;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator3;

public class SplashFragmentFooter extends BaseFragment<LoginActivityPresenter, SplashActivity> {
    @BindView(R.id.indicator)
    protected CircleIndicator3 indicator3;
    @BindView(R.id.btn_login)
    protected Button btnLogin;
    @BindView(R.id.btn_signup)
    protected Button btnSignup;
    @BindView(R.id.txt_create_acc)
    protected TextView txtCreateAcc;
    @BindView(R.id.txt_question_acc)
    protected TextView txtQuestionAcc;
    @BindView(R.id.layout_txt)
    protected LinearLayout layoutTxt;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_splash_footer;
    }

    @Override
    public void onPrepareLayout() {
        if (SplashFragmentContent.getmInstance() != null) {
            indicator3.setViewPager(SplashFragmentContent.getmInstance().getRcySplash());
        }
        setListener();
    }

    public void setListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment contentFragment = getBaseActivity().getSupportFragmentManager().findFragmentByTag("CONTENT_VIEWPAGER");
                if (contentFragment != null) {
                    layoutTxt.setVisibility(View.VISIBLE);
                    btnSignup.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public LoginActivityPresenter onCreatePresenter() {
        return null;
    }
}
