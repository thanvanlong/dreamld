package com.example.dreamtale.ui.login;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseCallback;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.network.ServiceBuilder;
import com.example.dreamtale.network.dto.AuthRequestBody;
import com.example.dreamtale.network.dto.DeviceInfo;
import com.example.dreamtale.utils.DeviceUtils;
import com.example.dreamtale.utils.DialogUtils;
import com.example.dreamtale.utils.PrefManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator3;

public class SplashFragmentFooter extends BaseFragment<LoginPresenter, SplashActivity> implements LoginView {
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
    private String status = "GO_TO_LOGIN";

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
                handleLayout();
            }
        });

        txtCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogin.setText("Next");
                SplashFragmentContent.getmInstance().getEdtPhone().setText("");
                layoutTxt.setVisibility(View.INVISIBLE);
                SplashFragmentContent.getmInstance().getContainerEdtPass().setVisibility(View.INVISIBLE);
                btnLogin.setTag("next_sign_up_pass");
                SplashFragmentHeader.getmInstance().getTxtHeader().setText("New Account");
                SplashFragmentHeader.getmInstance().getImgHeader().setImageResource(R.drawable.img_gate);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogin.setText("Next");
                SplashFragmentContent.getmInstance().getEdtPhone().setText("");
                btnLogin.setTag("next_sign_up_pass");
                btnSignup.setVisibility(View.INVISIBLE);
                if (SplashFragmentHeader.getmInstance() != null) {
                    SplashFragmentHeader.getmInstance().getImgHeader().setVisibility(View.VISIBLE);
                    SplashFragmentHeader.getmInstance().getTxtHeader().setText("New Account");
                    SplashFragmentContent.getmInstance().getRcySplash().setVisibility(View.GONE);
                    SplashFragmentContent.getmInstance().getContainerBtn().setVisibility(View.VISIBLE);
                    SplashFragmentContent.getmInstance().getContainerEdtPass().setVisibility(View.INVISIBLE);
                    SplashFragmentHeader.getmInstance().getImgHeader().setImageResource(R.drawable.img_gate);
                }
                indicator3.setVisibility(View.GONE);
            }
        });

    }

    private void handleLayout () {
        switch (btnLogin.getTag().toString()) {
            case "go_to_login":
                layoutTxt.setVisibility(View.VISIBLE);
                if (SplashFragmentHeader.getmInstance() != null) {
                    SplashFragmentHeader.getmInstance().getImgHeader().setVisibility(View.VISIBLE);
                    SplashFragmentHeader.getmInstance().getImgHeader().setImageResource(R.drawable.img_key);
                    SplashFragmentHeader.getmInstance().getTxtHeader().setText("Sign in");
                    SplashFragmentContent.getmInstance().getRcySplash().setVisibility(View.GONE);
                    SplashFragmentContent.getmInstance().getContainerBtn().setVisibility(View.VISIBLE);
                }
                btnSignup.setVisibility(View.GONE);
                indicator3.setVisibility(View.GONE);
                btnLogin.setTag("login");
                break;
            case "login":
                if (SplashFragmentContent.getmInstance().getEdtPass().getText().toString().matches("") || SplashFragmentContent.getmInstance().getEdtPhone().getText().toString().matches("")) {
                    Toast.makeText(getViewContext(), "Please enter your phone number and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                DeviceInfo deviceInfo = DeviceUtils.getDeviceInfo(getViewContext());
                AuthRequestBody authRequestBody = new AuthRequestBody(SplashFragmentContent.getmInstance().getEdtPhone().getText().toString(),
                        SplashFragmentContent.getmInstance().getEdtPass().getText().toString(), deviceInfo);
                if (authRequestBody != null) {
                    getPresenter().doLogin(authRequestBody);
                }
                break;
            case "next_sign_up_pass":
                if (SplashFragmentContent.getmInstance().getEdtPhone().getText().toString().matches("")){
                    DialogUtils.showToastMessage("Please enter your phone number!!", getViewContext(), false);
                    break;
                }
                getPresenter().checkPhoneNumber(SplashFragmentContent.getmInstance().getEdtPhone().getText().toString());
                break;
            case "next_sign_up_name":
                SplashFragmentContent.getmInstance().getTxtErrorLogin().setVisibility(View.GONE);
                SplashFragmentContent.getmInstance().getContainerEdtPhone().setVisibility(View.GONE);
                SplashFragmentContent.getmInstance().getContainerEdtPass().setVisibility(View.GONE);
                SplashFragmentContent.getmInstance().getContainerBtn().setVisibility(View.GONE);
                SplashFragmentContent.getmInstance().getContainerEdtUsername().setVisibility(View.VISIBLE);
                SplashFragmentHeader.getmInstance().getTxtHeader().setText("What's your name?");
                btnLogin.setTag("next_sign_up");
                break;
            case "next_sign_up":
                getPresenter().doRegister(new AuthRequestBody(
                        SplashFragmentContent.getmInstance().getEdtPhone().getText().toString(),
                        SplashFragmentContent.getmInstance().getEdtPass().getText().toString(),
                        "Long dep trai vc"
                ));
        }

        SplashFragmentContent.getmInstance().getTxtErrorLogin().setVisibility(View.GONE);
    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return new LoginPresenterImpl(this);
    }

    @Override
    public void loginSuccess(AuthRequestBody data) {
        Toast.makeText(getViewContext(), data.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail(String message) {
        Toast toast = Toast.makeText(getViewContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void registerFail(String message) {
        DialogUtils.showToastMessage(message, getViewContext(), false);
    }

    @Override
    public void isPhoneExist(String message) {
        DialogUtils.showToastMessage(message, getViewContext(), false);
    }

    @Override
    public void isPhoneNonExist() {
        btnLogin.setTag("next_sign_up_name");
        SplashFragmentContent.getmInstance().getContainerEdtPhone().setVisibility(View.GONE);
        SplashFragmentContent.getmInstance().getContainerEdtPass().setVisibility(View.VISIBLE);
        SplashFragmentHeader.getmInstance().getImgHeader().setImageResource(R.drawable.img_lock);
        SplashFragmentHeader.getmInstance().getTxtHeader().setText("Create new password");
    }

    @Override
    public void onGetOtpSuccess(int timeCountdown) {

    }

    @Override
    public void onGetOtpFail() {

    }

    @Override
    public void onChangePasswordSuccess() {

    }

    @Override
    public void onLimitedDevice() {

    }

    @Override
    public void showListDevices(List<DeviceInfo> data) {
        DeviceDialogFragment deviceDialogFragment = new DeviceDialogFragment();
        deviceDialogFragment.setListener(new DeviceDialogFragment.DialogDeviceListener() {
            @Override
            public void onRemoveSuccess() {
                getPresenter().doLogin(new AuthRequestBody(SplashFragmentContent.getmInstance().getEdtPhone().getText().toString(),
                        SplashFragmentContent.getmInstance().getEdtPass().getText().toString(),
                        DeviceUtils.getDeviceInfo(getViewContext())));
            }
        });
        deviceDialogFragment.init(getViewContext(), data, PrefManager.getAccessToken(getViewContext()));
        deviceDialogFragment.show(getViewContext().getSupportFragmentManager(), "Dialog");
    }
}
