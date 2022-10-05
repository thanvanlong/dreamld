package com.example.dreamtale.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.network.dto.AuthRequestBody;
import com.example.dreamtale.network.dto.ContentSplash;
import com.example.dreamtale.network.dto.DeviceInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SplashFragmentContent extends BaseFragment<LoginPresenter, SplashActivity> implements LoginView {

    @BindView(R.id.rcy_splash)
    protected ViewPager2 rcySplash;
    @BindView(R.id.container_btn)
    protected LinearLayout containerBtn;
    @BindView(R.id.edt_phone)
    protected EditText edtPhone;
    @BindView(R.id.edt_pass)
    protected EditText edtPass;
    @BindView(R.id.btn_login_gg)
    protected Button btnLoginGg;
    @BindView(R.id.btn_login_fb)
    protected Button btnLoginFb;
    @BindView(R.id.container_edt_pass)
    protected LinearLayout containerEdtPass;
    @BindView(R.id.container_edt_phone)
    protected LinearLayout containerEdtPhone;
    @BindView(R.id.txt_error_login)
    protected TextView txtErrorLogin;
    @BindView(R.id.container_edt_username)
    protected LinearLayout containerEdtUsername;

    public LinearLayout getContainerEdtUsername() {
        return containerEdtUsername;
    }

    public void setContainerEdtUsername(LinearLayout containerEdtUsername) {
        this.containerEdtUsername = containerEdtUsername;
    }

    public TextView getTxtErrorLogin() {
        return txtErrorLogin;
    }

    public void setTxtErrorLogin(TextView txtErrorLogin) {
        this.txtErrorLogin = txtErrorLogin;
    }

    public LinearLayout getContainerEdtPass() {
        return containerEdtPass;
    }

    public void setContainerEdtPass(LinearLayout containerEdtPass) {
        this.containerEdtPass = containerEdtPass;
    }

    public LinearLayout getContainerEdtPhone() {
        return containerEdtPhone;
    }

    public void setContainerEdtPhone(LinearLayout containerEdtPhone) {
        this.containerEdtPhone = containerEdtPhone;
    }

    public LinearLayout getContainerBtn() {
        return containerBtn;
    }

    public void setContainerBtn(LinearLayout containerBtn) {
        this.containerBtn = containerBtn;
    }

    public EditText getEdtPhone() {
        return edtPhone;
    }

    public void setEdtPhone(EditText edtPhone) {
        this.edtPhone = edtPhone;
    }

    public EditText getEdtPass() {
        return edtPass;
    }

    public void setEdtPass(EditText edtPass) {
        this.edtPass = edtPass;
    }

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
    public LoginPresenter onCreatePresenter() {
        return null;
    }

    @Override
    public void loginSuccess(AuthRequestBody data) {

    }

    @Override
    public void loginFail(String message) {

    }

    @Override
    public void registerFail(String message) {

    }

    @Override
    public void isPhoneExist(String message) {

    }

    @Override
    public void isPhoneNonExist() {

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

    }
}
