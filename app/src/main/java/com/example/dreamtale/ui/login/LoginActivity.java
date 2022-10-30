package com.example.dreamtale.ui.login;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseActivity;

import butterknife.BindView;


public class LoginActivity extends BaseActivity<LoginPresenter> {

    @BindView(R.id.frg_login)
    protected FrameLayout mFrgLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onPrepareLayout() {
        Intent result = getIntent();
        int code = result.getIntExtra("type_screen", -1);
        if (code == OnBoardingActivity.ACTIVITY_LOGIN){
            addFragment(R.id.frg_login, new LoginFragment(), false, LoginFragment.class.getSimpleName());
        } else if (code == OnBoardingActivity.ACTIVITY_SIGNUP) {
            addFragment(R.id.frg_login, new SignupFragment(), false, SignupFragment.class.getSimpleName() );
        }

        mFrgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyboard();
            }
        });

    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return null;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.popBackStack();
    }
}
